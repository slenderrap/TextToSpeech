package com.project.texttospeech

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.texttospeech.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {
    interface OnInitListener
    private var tts :TextToSpeech? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tts = TextToSpeech(applicationContext){ status ->
            if (status != TextToSpeech.ERROR){
                tts?.language = Locale.getDefault()
            }

        }
        binding.button.setOnClickListener {
            val toSpeak = binding.editText.text.toString()
            Toast.makeText(applicationContext, toSpeak,Toast.LENGTH_SHORT).show()
            binding.editText.text
            tts?.speak(toSpeak, TextToSpeech.QUEUE_FLUSH,null,null)
        }
    }

    override fun onPause() {
        tts?.stop()
        tts?.shutdown()
        super.onPause()
    }
}