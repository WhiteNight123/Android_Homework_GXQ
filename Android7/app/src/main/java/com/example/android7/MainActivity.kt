package com.example.android7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.android7.demo1.RecyclerViewDemo1
import com.example.android7.demo2.RecyclerViewDemo2
import com.example.android7.demo3.RecyclerViewDemo3
import com.example.android7.demo4.Paging3Demo

class MainActivity : AppCompatActivity() {
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1 = findViewById(R.id.recyclerView1_btn)
        button2 = findViewById(R.id.recyclerView2_btn)
        button3 = findViewById(R.id.recyclerView3_btn)
        button4 = findViewById(R.id.recyclerView4_btn)

        button1.setOnClickListener {
            val intent = Intent(this, RecyclerViewDemo1::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, RecyclerViewDemo2::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val intent = Intent(this, RecyclerViewDemo3::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(this, Paging3Demo::class.java)
            startActivity(intent)
        }

    }
}