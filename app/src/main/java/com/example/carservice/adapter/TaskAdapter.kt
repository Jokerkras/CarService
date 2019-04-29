package com.example.carservice.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carservice.R
import com.example.carservice.database.AppDatabase
import kotlinx.android.synthetic.main.task_item.view.*

class TaskAdapter(var items: List<Long>?, val context: Context): RecyclerView.Adapter<TaskAdapter.TaskAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapterViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false)
        return TaskAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: TaskAdapterViewHolder, position: Int) {
        val item = items?.get(position)
        if(item!= null) {
            val text = AppDatabase.getAppDataBase(context)?.taskDao()?.getTask(item)?.name ?: "error"
            Log.d("smth", text)
            holder.tvTask.text = text
        }
    }

    class TaskAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var tvTask = itemView.tvTask
    }
}