package com.example.carservice.activity

import android.content.Intent
import android. support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.carservice.R
import com.example.carservice.adapter.OrderAdapter
import com.example.carservice.database.AppDatabase
import com.example.carservice.model.Order
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: OrderAdapter
    private var orders: List<Order> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rvOrders.layoutManager = linearLayoutManager

        btnAdd.setOnClickListener { openAddActivity() }
    }

    override fun onResume() {
        super.onResume()
        orders = AppDatabase.getAppDataBase(this@MainActivity)?.orderDao()?.getAll() ?: emptyList()
        orders = orders.sortedByDescending { it.Date }
        adapter = OrderAdapter(orders, this, this)
        rvOrders.adapter = adapter
    }

    private fun openAddActivity(){
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View?) {
        if(view != null) {
            val itemPosition = rvOrders.getChildLayoutPosition(view)
            orders[itemPosition].Ready = true
            AppDatabase.getAppDataBase(this@MainActivity)?.orderDao()?.updateOrder(orders[itemPosition])
            adapter.notifyDataSetChanged()
        }
    }
}
