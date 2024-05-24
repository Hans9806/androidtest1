package com.busanit.test1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.busanit.test1.databinding.TodoItemBinding

class ToDoAdapter(
    private val toDoList: MutableList<ToDoItem>,
    private val onItemClick: (ToDoItem) -> Unit
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(), ItemTouchHelperAdapter {

    inner class ToDoViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(toDoItem: ToDoItem) {
            binding.toDoText.text = toDoItem.text
            binding.root.setOnClickListener { onItemClick(toDoItem) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(toDoList[position])
    }

    override fun getItemCount(): Int = toDoList.size

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val movedItem = toDoList.removeAt(fromPosition)
        toDoList.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, movedItem)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        toDoList.removeAt(position)
        notifyItemRemoved(position)
    }
}
