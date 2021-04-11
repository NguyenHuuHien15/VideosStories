package com.test.videosstories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.videosstories.common.MyApplication
import com.test.videosstories.common.di.AppComponent

class MainActivity : AppCompatActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as MyApplication).appComponent
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}