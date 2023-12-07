package com.example.myapplication_1207

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_SOEECH_INPUT = 1001
    private val myEmptyCode = ""
    private lateinit var btn:Button
    private lateinit var tvshow:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.btnOK)
        tvshow = findViewById(R.id.showMsg)

        btn.setOnClickListener {
             startSpeech()
        }
    }

    private fun startSpeech() {
        var intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault()) //抓取手機系統設定的語系
            putExtra(RecognizerIntent.EXTRA_PROMPT,"請說話")
            // startActivity(this)
        }
        try {
            startActivityForResult(intent,REQUEST_CODE_SOEECH_INPUT)
        }
        catch (ex:Exception){
            Log.d("myTag",ex.toString())  //Dump error message
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null){
            val result = data.getShortArrayExtra(RecognizerIntent.EXTRA_RESULTS)  //擷取辨識後的結果，可能有超過一個以上
            val spokenText = result?.get(0) ?:""  //得到 speech 的文字   //val spokenText = result?.get(0) ?:myEmptyCode
            checkSponkenText(spokenText as String)
        }
        else{  //不OK的處理
            Log.d("myTag","回傳錯誤")
        }
    }

    private fun checkSponkenText(s:String) {
        //依據文字處理動作
//        if (s!="-1"){  //s!=myEmptyCode
            tvshow.text = s
        when(s){
            "rigth" -> tvshow.text = "右"
            "left" -> tvshow.text = "左"
        }
//        }

    }
}