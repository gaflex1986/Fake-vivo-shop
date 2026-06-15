package com.example.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.VivoShopApplication
import com.example.data.Phone
import com.example.data.PhoneDataProvider
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen {
    object Catalog : Screen()
    object Cart : Screen()
    object Checkout : Screen()
}

data class SavedCard(
    val number: String,
    val holder: String,
    val expiry: String,
    val cvv: String
)

data class PaymentSuccessDetails(
    val amount: Int,
    val cardLast4: String,
    val orderId: String
)

class ShopViewModel : ViewModel() {

    // Фильтрация и поиск
    private val _selectedBrand = MutableStateFlow("Все")
    val selectedBrand: StateFlow<String> = _selectedBrand.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Навигация
    private val _currentScreen = MutableStateFlow<Screen>(Screen.Catalog)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    // Детализация выбранного телефона (Bottom Sheet / Detail Dialog)
    private val _selectedPhone = MutableStateFlow<Phone?>(null)
    val selectedPhone: StateFlow<Phone?> = _selectedPhone.asStateFlow()

    // Корзина в памяти приложения: Phone -> Количество
    private val _cartItems = MutableStateFlow<Map<Phone, Int>>(emptyMap())
    val cartItems: StateFlow<Map<Phone, Int>> = _cartItems.asStateFlow()

    // Общая стоимость корзины
    private val _cartTotal = MutableStateFlow(0)
    val cartTotal: StateFlow<Int> = _cartTotal.asStateFlow()

    // Вводы для страницы оформления / оплаты
    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _cardHolder = MutableStateFlow("")
    val cardHolder: StateFlow<String> = _cardHolder.asStateFlow()

    private val _cardExpiry = MutableStateFlow("")
    val cardExpiry: StateFlow<String> = _cardExpiry.asStateFlow()

    private val _cardCvv = MutableStateFlow("")
    val cardCvv: StateFlow<String> = _cardCvv.asStateFlow()

    // Опция сохранения способа оплаты (чекбокс)
    private val _saveCardChecked = MutableStateFlow(true)
    val saveCardChecked: StateFlow<Boolean> = _saveCardChecked.asStateFlow()

    // Список сохраненных способов оплаты
    private val _savedCards = MutableStateFlow<List<SavedCard>>(emptyList())
    val savedCards: StateFlow<List<SavedCard>> = _savedCards.asStateFlow()

    // Информация об успешной оплате для выпадающей плашки (Material You)
    private val _paymentSuccess = MutableStateFlow<PaymentSuccessDetails?>(null)
    val paymentSuccess: StateFlow<PaymentSuccessDetails?> = _paymentSuccess.asStateFlow()

    // Ивент вызова тостов в MainActivity
    private val _toastFlow = MutableSharedFlow<String>()
    val toastFlow: SharedFlow<String> = _toastFlow.asSharedFlow()

    // Список телефонов с учетом фильтров и поиска
    val filteredPhonesList: StateFlow<List<Phone>> = combine(
        _selectedBrand,
        _searchQuery
    ) { brand, query ->
        PhoneDataProvider.phonesList.filter { phone ->
            val brandMatch = if (brand == "Все") true else phone.brand.equals(brand, ignoreCase = true)
            val queryMatch = if (query.isBlank()) {
                true
            } else {
                phone.name.contains(query, ignoreCase = true) ||
                        phone.description.contains(query, ignoreCase = true) ||
                        phone.brand.contains(query, ignoreCase = true)
            }
            brandMatch && queryMatch
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        // Подсчет суммы при изменениях в корзине
        viewModelScope.launch {
            _cartItems.collect { cart ->
                val total = cart.entries.sumOf { it.key.price * it.value }
                _cartTotal.value = total
            }
        }
        // Загрузка сохраненных карт
        try {
            _savedCards.value = loadSavedCards()
        } catch (_: Exception) {}
    }

    // Сохранение и получение способов оплаты из SharedPreferences
    private fun loadSavedCards(): List<SavedCard> {
        val prefs = VivoShopApplication.instance.getSharedPreferences("saved_cards_pref", Context.MODE_PRIVATE)
        val serialized = prefs.getString("cards", "") ?: ""
        if (serialized.isEmpty()) return emptyList()
        return try {
            serialized.split("|||").mapNotNull { cardStr ->
                val parts = cardStr.split(":::")
                if (parts.size >= 4) {
                    SavedCard(parts[0], parts[1], parts[2], parts[3])
                } else null
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun saveCardsToPrefs(cards: List<SavedCard>) {
        val prefs = VivoShopApplication.instance.getSharedPreferences("saved_cards_pref", Context.MODE_PRIVATE)
        val serialized = cards.joinToString("|||") { "${it.number}:::${it.holder}:::${it.expiry}:::${it.cvv}" }
        prefs.edit().putString("cards", serialized).apply()
    }

    fun selectSavedCard(card: SavedCard) {
        _cardNumber.value = card.number
        _cardHolder.value = card.holder
        _cardExpiry.value = card.expiry
        _cardCvv.value = card.cvv
        viewModelScope.launch {
            _toastFlow.emit("Способ оплаты •••• ${card.number.takeLast(4)} выбран")
        }
    }

    fun deleteSavedCard(card: SavedCard) {
        val current = _savedCards.value.filter { it.number != card.number }
        _savedCards.value = current
        saveCardsToPrefs(current)
        viewModelScope.launch {
            _toastFlow.emit("Способ оплаты удален")
        }
    }

    fun toggleSaveCard() {
        _saveCardChecked.value = !_saveCardChecked.value
    }

    fun dismissPaymentSuccess() {
        _paymentSuccess.value = null
    }

    // Изменение фильтров
    fun selectBrand(brand: String) {
        _selectedBrand.value = brand
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun navigateTo(screen: Screen) {
        _currentScreen.value = screen
    }

    fun viewPhoneDetails(phone: Phone?) {
        _selectedPhone.value = phone
    }

    // Функции корзины
    fun addToCart(phone: Phone) {
        val current = _cartItems.value.toMutableMap()
        current[phone] = (current[phone] ?: 0) + 1
        _cartItems.value = current
        viewModelScope.launch {
            _toastFlow.emit("«${phone.name}» добавлен в корзину")
        }
    }

    fun decreaseQuantity(phone: Phone) {
        val current = _cartItems.value.toMutableMap()
        val count = current[phone] ?: return
        if (count <= 1) {
            current.remove(phone)
        } else {
            current[phone] = count - 1
        }
        _cartItems.value = current
    }

    fun removeFromCart(phone: Phone) {
        val current = _cartItems.value.toMutableMap()
        current.remove(phone)
        _cartItems.value = current
    }

    // Функции оплаты
    fun updateCardNumber(number: String) {
        // Ограничиваем только цифрами до 16 символов
        val cleanNumber = number.filter { it.isDigit() }.take(16)
        _cardNumber.value = cleanNumber
    }

    fun updateCardHolder(holder: String) {
        _cardHolder.value = holder.uppercase().filter { it.isLetter() || it == ' ' }
    }

    fun updateCardExpiry(expiry: String) {
        val cleanExpiry = expiry.filter { it.isDigit() }.take(4)
        _cardExpiry.value = cleanExpiry
    }

    fun updateCardCvv(cvv: String) {
        val cleanCvv = cvv.filter { it.isDigit() }.take(3)
        _cardCvv.value = cleanCvv
    }

    fun payOrder() {
        val num = _cardNumber.value
        val expiry = _cardExpiry.value
        val cvv = _cardCvv.value
        val holder = _cardHolder.value

        if (num.length < 16) {
            viewModelScope.launch {
                _toastFlow.emit("Введите корректный номер карты (16 цифр)")
            }
            return
        }
        if (expiry.length < 4) {
            viewModelScope.launch {
                _toastFlow.emit("Введите срок действия карты (ММ/ГГ)")
            }
            return
        }
        if (cvv.length < 3) {
            viewModelScope.launch {
                _toastFlow.emit("Введите трёхзначный CVV код")
            }
            return
        }
        if (holder.isBlank()) {
            viewModelScope.launch {
                _toastFlow.emit("Введите имя держателя карты")
            }
            return
        }

        // Сохраняем карту если выбран чекбокс
        if (_saveCardChecked.value) {
            val exists = _savedCards.value.any { it.number == num }
            if (!exists) {
                val updated = _savedCards.value + SavedCard(num, holder, expiry, cvv)
                _savedCards.value = updated
                saveCardsToPrefs(updated)
            }
        }

        val totalAmount = _cartTotal.value
        val last4 = num.takeLast(4)

        // Успешная оплата!
        viewModelScope.launch {
            // Toast с точным текстом из ТЗ
            _toastFlow.emit("Оплата прошла успешно! Ваш заказ оформлен")
            
            // Фиксируем информацию для выпадающей плашки
            val orderId = "VS-${(100000..999999).random()}"
            _paymentSuccess.value = PaymentSuccessDetails(amount = totalAmount, cardLast4 = last4, orderId = orderId)

            // Очищаем корзину и поля ввода после покупки
            _cartItems.value = emptyMap()
            _cardNumber.value = ""
            _cardExpiry.value = ""
            _cardCvv.value = ""
            _cardHolder.value = ""
            
            // Возвращаем на вкладку каталог
            _currentScreen.value = Screen.Catalog
        }
    }
}
