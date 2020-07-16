package com.example.playsoundsample

import android.annotation.TargetApi
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 初回のみ再生出来ないのでgetInstanceに触れて初期化しておく
        Sound.getInstance(this)

        findViewById<Button>(R.id.button).setOnClickListener {
            Sound.getInstance(this).playSound(Sound.numberList[0])
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            Sound.getInstance(this).playSound(Sound.numberList[1])
        }

    }


}