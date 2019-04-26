package com.example.carservice.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.carservice.R
import com.example.carservice.adapter.OrderAdapter
import com.example.carservice.database.AppDatabase
import com.example.carservice.model.Order
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: OrderAdapter
    private var orders: List<Order> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvOrders.layoutManager = linearLayoutManager
        adapter = OrderAdapter(orders, this, View.OnClickListener {  })
        rvOrders.adapter = adapter

        btnAdd.setOnClickListener { openAddActivity() }

        doAsync {
            orders = AppDatabase.getAppDataBase(this@MainActivity)?.orderDao()?.getAll() ?: emptyList()
            adapter.notifyDataSetChanged()
        }
    }

    fun openAddActivity(){
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }
}
