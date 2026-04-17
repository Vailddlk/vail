package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var onCreateTime: Long = 0
    var onStartTime: Long = 0
    var onResumeTime: Long = 0
    var onPauseTime: Long = 0
    var onStopTime: Long = 0
    var onDestroyTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateTime = System.currentTimeMillis()
        Log.d("MyLifecycle", "onCreate")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        onStartTime = System.currentTimeMillis()
        Log.d("MyLifecycle", "onStart")

        val diff = onStartTime - onCreateTime
        Log.d("MyLifecycle", "Разница onCreate до onStart: $diff мс")
    }

    override fun onResume() {
        super.onResume()
        onResumeTime = System.currentTimeMillis()
        Log.d("MyLifecycle", "onResume")

        val diff = onResumeTime - onStartTime
        Log.d("MyLifecycle", "Разница onStart до onResume: $diff мс")
    }

    override fun onPause() {
        super.onPause()
        onPauseTime = System.currentTimeMillis()
        Log.d("MyLifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        onStopTime = System.currentTimeMillis()
        Log.d("MyLifecycle", "onStop")
        val diff = onStopTime - onPauseTime
        Log.d("MyLifecycle", "Разница onPause до onStop: $diff мс")
    }

    override fun onDestroy() {
        super.onDestroy()
        onDestroyTime = System.currentTimeMillis()
        Log.d("MyLifecycle", "onDestroy")
        val diff = onDestroyTime - onStopTime
        Log.d("MyLifecycle", "Разница onStop -> onDestroy: $diff мс")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MyLifecycle", "onRestart")
    }
}