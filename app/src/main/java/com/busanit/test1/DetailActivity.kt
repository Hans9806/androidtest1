package com.busanit.test1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.busanit.test1.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding 초기화
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트로부터 할 일 텍스트 가져와서 표시
        val toDoText = intent.getStringExtra("TODO_TEXT")
        binding.detailText.text = toDoText
        }
}