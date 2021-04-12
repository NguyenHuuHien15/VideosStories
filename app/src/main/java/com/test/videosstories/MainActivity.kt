package com.test.videosstories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.videosstories.common.MyApplication
import com.test.videosstories.common.di.AppComponent
import com.test.videosstories.common.di.MainComponent

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (application as MyApplication).appComponent.mainComponent().create()
        mainComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}