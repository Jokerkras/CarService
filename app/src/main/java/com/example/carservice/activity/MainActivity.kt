package com.example.carservice.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.carservice.R
import com.example.carservice.adapter.OrderAdapter
import com.example.carservice.model.Order
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: OrderAdapter
    private lateinit var orders: List<Order>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        orders = listOf()

        linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvOrders.layoutManager = linearLayoutManager
        adapter = OrderAdapter(orders, this, View.OnClickListener {  })
        rvOrders.adapter = adapter
    }
}
