package com.sandipbhattacharya.texttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.button_speak
import kotlinx.android.synthetic.main.activity_main.etditext_input
import org.w3c.dom.Text
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var buttonSpeak : Button? = null
    private var editText : EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSpeak = this.button_speak
        editText = this.etditext_input

        buttonSpeak!!.isEnabled = false
        tts = TextToSpeech(this,this)

        buttonSpeak!!.setOnClickListener {
            speak()
        }
    }
    override fun onInit(status: Int){

        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "THE LANGUAGE SPECIFIED IS NOT SUPPORTED!", Toast.LENGTH_SHORT).show()
            }else{
                buttonSpeak!!.isEnabled = true
            }
        }else{
            Toast.makeText(this, "INITILIZATION FAILED!", Toast.LENGTH_SHORT).show()
        }

    }
    private fun speak{
        val text = editText!!.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")

    }

    override fun speak(){
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}