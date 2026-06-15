package com.example

import android.app.Application
import com.google.android.material.color.DynamicColors

class VivoShopApplication : Application() {
    companion object {
        lateinit var instance: VivoShopApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // Применяем динамические цвета ко всем Activity в приложении
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}
