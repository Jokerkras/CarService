package com.example.carservice.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.carservice.R
import com.example.carservice.adapter.TaskAdapter
import com.example.carservice.database.AppDatabase
import com.example.carservice.model.Order
import kotlinx.android.synthetic.main.activity_description.*

class DescriptionActivity: AppCompatActivity() {
    lateinit var order: Order
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        val orderId = intent.getLongExtra("order", 1)

        order = AppDatabase.getAppDataBase(this)?.orderDao()?.getOrder(orderId)!!

        tvVIN.text = order.VIN
        tvMark.text = order.Mark
        tvModel.text = order.Model
        tvNumber.text = order.Number
        tvInfo.text = order.Info

        val linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvTasks.layoutManager = linearLayoutManager
        val tasks = AppDatabase.getAppDataBase(this)?.orderWithTaskDao()?.getOrderWithTasks(orderId)?.map { it.task_id }
        val adapter = TaskAdapter(tasks, this)
        rvTasks.adapter = adapter
    }
}