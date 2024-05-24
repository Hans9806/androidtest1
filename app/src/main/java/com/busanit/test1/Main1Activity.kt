package com.busanit.test1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.busanit.test1.databinding.ActivityMain1Binding

class Main1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMain1Binding
    private lateinit var toDoAdapter: ToDoAdapter
    private val toDoList = mutableListOf(
        ToDoItem("Sample Task 1"),
        ToDoItem("Sample Task 2"),
        ToDoItem("Sample Task 3")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding 초기화
        binding = ActivityMain1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // ToDoAdapter 초기화
        toDoAdapter = ToDoAdapter(toDoList) { toDoItem ->
            // 클릭 이벤트 핸들러 : 세부사항 화면으로 이동
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("TODO_TEXT", toDoItem.text)
            }
            startActivity(intent)
        }

        // RecyclerView 설정
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@Main1Activity)
            adapter = toDoAdapter
        }

        // ItemTouchHelper 설정
        val callback = ItemTouchHelperCallback(toDoAdapter)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
        // 추가 버튼 클릭 시 새로운 할 일 추가
        binding.addButton.setOnClickListener {
            val newTask = binding.editText.text.toString()
            if (newTask.isNotEmpty()) {
                toDoList.add(ToDoItem(newTask))
                toDoAdapter.notifyItemInserted(toDoList.size - 1)
                binding.editText.text.clear()
            }
        }
    }
}