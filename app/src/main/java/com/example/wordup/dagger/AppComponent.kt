package com.example.wordup.dagger

import android.app.Application
import com.example.wordup.main.WordUpActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    HttpClientModule::class,
    ViewModelModule::class
])
interface AppComponent {
    fun inject(app: Application)

    //Activities
    fun inject(mainActivity: WordUpActivity)
}