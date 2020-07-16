package com.example.playsoundsample

import android.annotation.TargetApi
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build

class Sound constructor(context: Context) {


    private var soundPool: SoundPool? = null
    private  val soudrawData = listOf(R.raw.papa1, R.raw.pafu1)


    companion object {
        val numberList = mutableListOf(0, 1)


        var INSTANCE:Sound? = null
        fun getInstance(context: Context) =
            INSTANCE ?: Sound(context).also {
                INSTANCE = it
            }

    }

    init {
        createSoundPool()
        loadSoundIDs(context)
    }

    private fun createSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createNewSoundPool()
        } else {
            createOldSoundPool()
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun createNewSoundPool() {
        val attributes = AudioAttributes.Builder().apply {
            setUsage(AudioAttributes.USAGE_GAME)
            setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)

        }.build()

        soundPool = SoundPool.Builder().apply {
            setMaxStreams(2)
            setAudioAttributes(attributes)
        }.build()
    }


    private fun createOldSoundPool() {
        soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
    }

    private fun loadSoundIDs(context:Context) {
        soundPool?.let {
            for (i in  numberList.indices)
                numberList[i] = it.load(context, soudrawData[i], 1)
        }
    }


    fun playSound(soundID:Int) {
        soundPool?.let{
            it.play(soundID, 1.0f, 1.0f, 1, 0, 1.0f)
            println("サウンド再生")
        }
    }


    fun close() { // シングルトンの場合呼びようがない？
        soundPool?.release()
        soundPool = null
    }
}