package com.example.wordup

import android.app.Application
import com.example.wordup.dagger.AppComponent
import com.example.wordup.dagger.AppModule
import com.example.wordup.dagger.DaggerAppComponent
import com.example.wordup.dagger.HttpClientModule

class Application : Application() {

    companion object {
        var dependencies: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        if (dependencies == null) {
            dependencies = DaggerAppComponent
                .builder()
                .httpClientModule(HttpClientModule())
                .appModule(AppModule(applicationContext))
                .build()
        }
    }
}