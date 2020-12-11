package com.example.timer

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.TextView
import android.widget.VideoView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var dateText: TextView
    lateinit var timerText: TextView
    lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getWindow().setFlags(
    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dateText = findViewById(R.id.DateText) as TextView
        timerText = findViewById(R.id.TimeText) as TextView
        videoView = findViewById(R.id.videoView) as VideoView
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.test_video));
        videoView.start();
        videoView.setOnCompletionListener { videoView.start() }
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val date = Calendar.getInstance().time
                val dateFormat = SimpleDateFormat("yyyy.MM.dd E요일")
                val timeFormat = SimpleDateFormat("H:mm:ss")
                var dateFormattedText = dateFormat.format(date)
                var timeFormattedText = timeFormat.format(date)
                GetNowTime(dateFormattedText,timeFormattedText)
            }
        }, 0, 1000)
    }

    fun GetNowTime(dateFormattedText:String,timeFormattedText:String){
        runOnUiThread {
            dateText.text = dateFormattedText
            timerText.text = timeFormattedText
        }
    }
}