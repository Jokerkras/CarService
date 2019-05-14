package com.example.carservice.activity

import android.content.Intent
import android. support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.carservice.R
import com.example.carservice.adapter.OrderAdapter
import com.example.carservice.database.AppDatabase
import com.example.carservice.model.Order
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        adapter = OrderAdapter(orders,
            this,
            //Обработка нажатия для открытия экрана с полным описанием
            View.OnClickListener {
                val intent = Intent(this, DescriptionActivity::class.java)
                val itemPosition = rvOrders.getChildLayoutPosition(it)
                intent.putExtra("order", orders[itemPosition].id)
                startActivity(intent)
            },
            //Обработка нажатия на кнопку закрытия
            View.OnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle(R.string.DialogTitle)
                builder.setMessage(R.string.DialogQuestion)
                builder.setPositiveButton(R.string.Yes){dialog, which ->
                    val parent = it.parent.parent as View
                    val itemPosition = rvOrders.getChildLayoutPosition(parent)
                    orders[itemPosition].Ready = true
                    AppDatabase.getAppDataBase(this@MainActivity)?.orderDao()?.updateOrder(orders[itemPosition])
                    adapter.notifyDataSetChanged()
                }
                builder.setNegativeButton(R.string.No){dialog,which ->
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
            )
        rvOrders.adapter = adapter
    }

    private fun openAddActivity(){
        val intent = Intent(this, AddActivity::class.java)
        startActivity(intent)
    }
}
