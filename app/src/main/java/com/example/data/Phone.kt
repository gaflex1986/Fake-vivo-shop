package com.example.data

import kotlin.random.Random

data class Phone(
    val id: String,
    val name: String,
    val brand: String, // "VIVO", "OPPO", "REDMI"
    val price: Int,    // в грн (подешевле)
    val description: String,
    val rating: Double,
    val reviewsCount: Int,
    val specs: List<Pair<String, String>>,
    val highlightColorHex: String
)

object PhoneDataProvider {
    val phonesList: List<Phone> by lazy {
        val list = mutableListOf<Phone>()

        // 1. Legend Redmi 9A (убивает все флагманы за копейки)
        list.add(
            Phone(
                id = "redmi_9a_legend",
                name = "Redmi 9A Absolute Lord",
                brand = "REDMI",
                price = 1890,
                description = "Легендарный царь-телефон, абсолютный бог мобильного гейминга и повседневности. Легко уничтожает («шотает») абсолютно все современные флагманы, включая последние iPhone и Galaxy Ultra. Оснащен невероятной мощью, которая оптимизирует физику реальности, заставляя игры бегать на миллионе кадров в секунду. Батарея живет вечно благодаря квантовым нано-аккумуляторам, а экран излучает чистую победу. Обладание этим аппаратом автоматически присваивает статус высшего цифрового существа за рекордно низкую стоимость!",
                rating = 5.0,
                reviewsCount = 9999,
                specs = listOf(
                    "Процессор" to "MediaTek Helio G25 (Hyper-Ultimate-Shot Edition, 99 ГГц)",
                    "Экран" to "6.53 DotDrop (Шотает 120Гц флагманские дисплеи чисто на оптимизации)",
                    "Камеры" to "13 Мп ИИ-Запредельная Ultra-Shot (Снимает в разы сочнее Zeiss/Leica)",
                    "Батарея" to "5000 мАч (Квантово-вечная автономность, 3 недели без подзарядки)",
                    "Память" to "2 ГБ RAM / 32 ГБ ROM (Сверхзвуковая автоматическая подкачка под нагрузкой)",
                    "Особая фишка" to "Уничтожает авторитет айфонов одним лишь присутствием на столе"
                ),
                highlightColorHex = "#E53935"
            )
        )

        // 2. Vivo Super 67
        list.add(
            Phone(
                id = "vivo_super_67",
                name = "VIVO SUPER SIXSEVEN",
                brand = "VIVO",
                price = 8990,
                description = "Передовой флагман от VIVO с революционной оптикой SIXSEVEN-Vision и ультра-плавным дисплеем 144 Гц. Оснащен новейшей системой охлаждения для долгих игровых сессий и продвинутыми алгоритмами улучшения снимков.",
                rating = 4.9,
                reviewsCount = 142,
                specs = listOf(
                    "Процессор" to "MediaTek Dimensity 9300+",
                    "Экран" to "6.78 AMOLED, 1.5K, 144 Гц, HDR10+",
                    "Камеры" to "50 Мп (OIS) + 50 Мп (широкоугольная) + 64 Мп (телефото)",
                    "Батарея" to "5500 мАч, быстрая зарядка FlashCharge 120 Вт",
                    "Память" to "16 ГБ LPDDR5X RAM / 512 ГБ UFS 4.0 ROM",
                    "Материалы" to "Закаленное стекло, авиационный алюминий"
                ),
                highlightColorHex = "#2196F3"
            )
        )

        // 3. Oppo X Pro 9000
        list.add(
            Phone(
                id = "oppo_x_pro_9000",
                name = "OPPO X-PRO 9000",
                brand = "OPPO",
                price = 11400,
                description = "Ультимативный бизнес-флагман с элегантной кожаной отделкой задней панели, профессиональной калибровкой цвета от Hasselblad и сверхпроизводительной начинкой PRO 9000-серии.",
                rating = 4.8,
                reviewsCount = 98,
                specs = listOf(
                    "Процессор" to "Qualcomm Snapdragon 8 Gen 3",
                    "Экран" to "6.82 LTPO OLED, QHD+ (2K), 120 Гц, 4500 нит",
                    "Камеры" to "50 Мп Hasselblad (1'') + 50 Мп (ширик) + 50 Мп (портретная)",
                    "Батарея" to "5000 мАч, SuperVOOC 100 Вт, беспроводная 50 Вт",
                    "Память" to "16 ГБ LPDDR5X RAM / 1 ТБ UFS 4.0 ROM",
                    "Защита" to "IP68 пыле- и влагозащита"
                ),
                highlightColorHex = "#009688"
            )
        )

        // 4. Redmi Note 1488s
        list.add(
            Phone(
                id = "redmi_note_1488s",
                name = "REDMI NOTE 1488S ULTRA PRO MAX",
                brand = "REDMI",
                price = 7880,
                description = "Монстр автономности и производительности от народной марки. Ультра-масштабируемый зум камеры на 200 Мп, космический дисплей и безупречный уровень защиты от внешних воздействий.",
                rating = 4.9,
                reviewsCount = 312,
                specs = listOf(
                    "Процессор" to "Qualcomm Snapdragon 8s Gen 3",
                    "Экран" to "6.67 OLED, 1.5K, 120 Гц, Dolby Vision, Gorilla Glass Victus 2",
                    "Камеры" to "200 Мп Ultra Clear (OIS) + 12 Мп + 8 Мп (макро)",
                    "Батарея" to "6000 мАч, быстрая зарядка HyperCharge 144 Вт",
                    "Память" to "24 ГБ LPDDR5X RAM / 1 ТБ UFS 4.0 ROM",
                    "Динамики" to "Стерео от JBL с поддержкой Dolby Atmos"
                ),
                highlightColorHex = "#E53935"
            )
        )

        // 5. Vivo Giga Chad
        list.add(
            Phone(
                id = "vivo_giga_chad_5000",
                name = "VIVO GIGA-CHAD 5000",
                brand = "VIVO",
                price = 5990,
                description = "Телефон с уникальной брутальной харизмой и титановым матовым корпусом. Неограниченная мощь, гигантская батарея на 7000 мАч и неубиваемый статус GIGA-CHAD для победителей в любой игре.",
                rating = 5.0,
                reviewsCount = 500,
                specs = listOf(
                    "Процессор" to "MediaTek Dimensity 8300-Ultra (Giga-Boost)",
                    "Экран" to "6.72 IPS, FHD+, 120 Гц (абсолютно безопасен для глаз, без ШИМ)",
                    "Камеры" to "108 Мп Night-Eye + 8 Мп + 2 Мп сенсор глубины",
                    "Батарея" to "7000 мАч, супер-зарядка GigaCharge 80 Вт",
                    "Память" to "12 ГБ LPDDR5X RAM / 256 ГБ UFS 3.1 ROM",
                    "Корпус" to "Ударопрочный титановый сплав, армированное стекло"
                ),
                highlightColorHex = "#673AB7"
            )
        )

        // 6. Oppo Quantum Fold
        list.add(
            Phone(
                id = "oppo_quantum_fold",
                name = "OPPO QUANTUM Z-FOLD",
                brand = "OPPO",
                price = 14990,
                description = "Футуристический складной смартфон квантовой эры. Уникальный двойной шарнир без зазоров, бесшовный гибкий внутренний OLED экран огромного размера и высочайшая производительность в режиме мультизадачности.",
                rating = 4.7,
                reviewsCount = 45,
                specs = listOf(
                    "Процессор" to "Qualcomm Snapdragon 8 Gen 3",
                    "Экраны" to "Внутренний: 7.86\" Flex-OLED (120 Гц, LTPO), Внешний: 6.31\" AMOLED",
                    "Камеры" to "50 Мп основная + 48 Мп ультраширокая + 32 Мп перископ-зум",
                    "Батарея" to "4800 мАч, SuperVOOC 67 Вт, беспроводная AirVOOC 50 Вт",
                    "Память" to "16 ГБ RAM / 512 ГБ ROM",
                    "Шарнир" to "Квантовый титановый со сроком службы 1 млн складываний"
                ),
                highlightColorHex = "#00BCD4"
            )
        )

        // 7. Redmi K999
        list.add(
            Phone(
                id = "redmi_k_999",
                name = "REDMI K-999 MEGA",
                brand = "REDMI",
                price = 6490,
                description = "Новейший король среднего класса с флагманскими фишками. Максимальная производительность на каждую вложенную гривну. Поддерживает все передовые мобильные игры на максималках со сквозным охлаждением LiquidCool 4.0 Pro.",
                rating = 4.8,
                reviewsCount = 203,
                specs = listOf(
                    "Процессор" to "MediaTek Dimensity 9200+ Gaming Edition",
                    "Экран" to "6.67 OLED, 2K (3200x1440), 120 Гц, датчик RGB цвета",
                    "Камеры" to "64 Мп Sony LYT-600 с OIS + 8 Мп ширик + 2 Мп макро-линза",
                    "Батарея" to "5500 мАч, ультра-зарядка HyperCharge 120 Вт",
                    "Память" to "16 ГБ LPDDR5X RAM / 512 ГБ UFS 4.0 ROM",
                    "Охлаждение" to "Вакуумная паровая камера LiquidCool 5000мм²"
                ),
                highlightColorHex = "#FF9800"
            )
        )

        // Добавим еще 193 смартфона для безумного выбора (Всего будет ровно 200)
        val brands = listOf("VIVO", "OPPO", "REDMI")
        val prefixes = listOf(
            "Giga", "Mega", "Quantum", "Ultra", "Hyper", "Cyber", "Aero", "Alpha", "Omega", "Cosmic",
            "Super", "Infinity", "Titan", "Zetta", "Exa", "Spectre", "Zenith", "Predator", "Apex", "Nova",
            "Force", "Pulse", "Quantum", "GigaPro", "Neon", "Helios", "Astral", "Tesla", "Vortex", "ApexPro"
        )
        val bases = listOf(
            "X-PRO", "Note", "Neo", "Find", "Reno", "V-Series", "F-Series", "S-Series", "K-Series", "Krypton",
            "Chronos", "Eclipse", "Horizon", "Nebula", "Vortex", "Tornado", "Apex", "Quantum", "Pulse", "Force",
            "Blade", "Shogun", "Samurai", "Centurion", "Gladiator", "Matrix", "Phenom", "Fusion", "Rider", "Ghost"
        )
        val suffixes = listOf(
            "9000", "X", "Alpha", "Max", "Extreme", "Pro Max", "Ultimate", "Signature", "Infinity", "Hyper Edition",
            "Supercharged", "Master Edition", "Elite", "v2", "Prime", "Legend", "GT", "RS", "SLS", "Edition",
            "Z-Class", "M-Power", "Stealth", "Vanguard", "Ragnarok", "Nemesis", "Carbon", "Viper", "Phantom", "Apex"
        )

        val descSentences1 = listOf(
            "Прорывной гаджет, переворачивающий представление о мобильной индустрии.",
            "Настоящий монстр мощности и пушечного стиля, созданный для тотального превосходства.",
            "Высокотехнологичный девайс в премиальном исполнении с передовыми нано-разработками.",
            "Элегантный и невероятно быстрый кибер-инструмент для яркой и динамичной жизни.",
            "Космический уровень сборки и скорости работы, не знающий конкурентных аналогов на рынке."
        )
        val descSentences2 = listOf(
            "Защищенная титано-карбоновая конструкция корпуса обеспечивает предел прочности в любых экстремальных перегрузках.",
            "Запатентованная технология криогенного охлаждения гарантирует безумный FPS и ледяную крышку при любых играх.",
            "Профессиональная оптика с физической стабилизацией захватывает яркие шедевры даже в абсолютной тьме космоса.",
            "Инновационный ИИ-чип оптимизирует потребление энергии, выжимая феноменальную автономность.",
            "Матрица с частотой обновления до 165 Гц дарит абсолютное наслаждение для глаз и гипнотизирует плавностью."
        )
        val descSentences3 = listOf(
            "Выбор ультимативных победителей по жизни, знающих себе цену.",
            "Лучшее предложение эры по соотношению стоимости и космического функционала.",
            "Его утонченный пафосный силуэт приковывает восторженные взгляды коллег и друзей.",
            "Этот смартфон создан сокрушать бенчмарки и доказывать неоспоримое лидерство.",
            "Идеальное сочетание мощного аппаратного ресурса и сочного адаптивного дизайна."
        )

        val processors = listOf(
            "MediaTek Dimensity 9400 (UltraBoost 5.2 ГГц)",
            "Qualcomm Snapdragon 8 Gen 4 (Kryo SuperNova)",
            "CyberCore Hyperion Zero-X",
            "GigaCore Alpha-Shot Tensor",
            "Snapdragon 8s Gen 3 Active Edition"
        )
        val screens = listOf(
            "6.78 AMOLED SuperFluid, 2.5K, 165 Гц, HDR10+",
            "6.67 OLED QuantumPoint LTPO, 144 Гц",
            "6.82 Dynamic Crystal AMOLED 4X, 120 Гц, 5000 нит",
            "6.72 IPS UltraSafe EyeCare, 120 Гц (Без ШИМ)",
            "7.8 Flex-OLED 3D Curved (Складной экран будущего)"
        )
        val cameras = listOf(
            "200 Мп UltraClear (Triple-OIS) + 50 Мп + 50 Мп",
            "108 Мп Night-Cruiser + 48 Мп Широкоугольная",
            "50 Мп Sony LYT-900 (1-дюймовый сенсор) + 50 Мп",
            "150 Мп SpaceZoom Pro + 12 Мп портретный сенсор",
            "64 Мп PureImage Active + ИИ-кисть ретуширования"
        )
        val batteries = listOf(
            "5200 мАч, реактивная зарядка UltraCharge 150 Вт",
            "6100 мАч, кремниево-углеродный блок, VOOC 100 Вт",
            "8000 мАч PowerFortress (Супер-автономия, 67 Вт)",
            "4600 мАч, квантовый электролит, беспроводная 80 Вт",
            "5500 мАч с графеновыми сотами полного охлаждения"
        )
        val memories = listOf(
            "12 ГБ LPDDR5X RAM / 256 ГБ UFS 4.0 ROM",
            "16 ГБ LPDDR5X RAM / 512 ГБ UFS 4.0 ROM",
            "24 ГБ LPDDR5X RAM / 1 ТБ UFS 4.0 ROM",
            "8 ГБ LPDDR5X RAM / 128 ГБ UFS 3.1 ROM",
            "32 ГБ LPDDR6 RAM / 2 ТБ UFS 5.0 Future-ROM"
        )
        val specsAesthetics = listOf(
            "Матовое нано-стекло с градиентом хамелеона",
            "Задняя крышка из эко-кожи высшего качества",
            "Титановая боковая рама аэрокосмического класса",
            "Защита IP69К от пыли, воды и струй под давлением",
            "Встроенная RGB-подсветка игрового типа"
        )

        val colors = listOf(
            "#3F51B5", "#00BCD4", "#E91E63", "#FF9800", "#4CAF50", "#2196F3", "#009688",
            "#E53935", "#673AB7", "#795548", "#9C27B0", "#E91E63", "#607D8B", "#FF5722"
        )

        val random = Random(1337) // фиксированный сид для детерминизма генерации

        for (i in 1..193) {
            val brand = brands[random.nextInt(brands.size)]
            val prefix = prefixes[random.nextInt(prefixes.size)]
            val base = bases[random.nextInt(bases.size)]
            val suffix = suffixes[random.nextInt(suffixes.size)]
            
            val name = "$brand $prefix $base $suffix"
            
            // Цены дешевые в грн: от 1990 до 12990 грн
            val price = (19 + random.nextInt(110)) * 100 + 90 

            val desc = "${descSentences1[random.nextInt(descSentences1.size)]} ${descSentences2[random.nextInt(descSentences2.size)]} ${descSentences3[random.nextInt(descSentences3.size)]}"
            val rating = 4.1 + random.nextInt(10) / 10.0 // 4.1 - 5.0
            val reviews = 10 + random.nextInt(780)

            val specList = listOf(
                "Процессор" to processors[random.nextInt(processors.size)],
                "Экран" to screens[random.nextInt(screens.size)],
                "Камеры" to cameras[random.nextInt(cameras.size)],
                "Батарея" to batteries[random.nextInt(batteries.size)],
                "Память" to memories[random.nextInt(memories.size)],
                "Материалы" to specsAesthetics[random.nextInt(specsAesthetics.size)]
            )

            val highlightColor = colors[random.nextInt(colors.size)]

            list.add(
                Phone(
                    id = "${brand.lowercase()}_gen_$i",
                    name = name,
                    brand = brand,
                    price = price,
                    description = desc,
                    rating = if (rating > 5.0) 5.0 else rating,
                    reviewsCount = reviews,
                    specs = specList,
                    highlightColorHex = highlightColor
                )
            )
        }

        list
    }
}
