package com.example.playvideotest

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fromAlbum = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView.setVideoURI(uri)

        button_pick.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "video/*"
            startActivityForResult(intent,fromAlbum)
        }

        button_play.setOnClickListener {
            if(!videoView.isPlaying)
                videoView.start()
        }

        button_pause.setOnClickListener {
            if(videoView.isPlaying)
                videoView.pause()
        }

        button_replay.setOnClickListener {
            if(videoView.isPlaying)
                videoView.resume()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            fromAlbum->{
                if(resultCode==Activity.RESULT_OK && data != null){
                    data.data?.let{uri->
                        videoView.setVideoURI(uri)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        super.onDestroy()
        videoView.suspend()
    }

}