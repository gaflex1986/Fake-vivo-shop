package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.ShopAppScreen
import com.example.ui.ShopViewModel
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                // Инициализация ViewModel и запуск главного экрана Vivo Shop
                val viewModel: ShopViewModel = viewModel()
                ShopAppScreen(viewModel = viewModel)
            }
        }
    }
}
