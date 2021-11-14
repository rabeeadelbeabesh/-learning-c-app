package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lesson_activity.*

class lesson_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_activity)
        val title=intent.extras?.get("title")
        val content=intent.extras?.get("con")
        title_text.text=title.toString()
        con_text.text=content.toString()

    }
}