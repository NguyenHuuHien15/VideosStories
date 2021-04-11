package com.test.videosstories.common

import android.app.Application
import com.test.videosstories.common.di.AppComponent
import com.test.videosstories.common.di.DaggerAppComponent

open class MyApplication : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}