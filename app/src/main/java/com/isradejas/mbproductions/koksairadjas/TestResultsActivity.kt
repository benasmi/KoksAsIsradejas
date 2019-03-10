package com.isradejas.mbproductions.koksairadjas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TestResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_results)

        val topic: Int = intent.getIntExtra("Topic",0)


    }
}
