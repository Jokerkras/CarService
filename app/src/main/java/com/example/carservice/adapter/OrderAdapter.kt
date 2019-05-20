package com.example.carservice.adapter

import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carservice.R
import com.example.carservice.database.AppDatabase
import com.example.carservice.model.Order
import kotlinx.android.synthetic.main.order_card.view.*
import java.text.SimpleDateFormat

class OrderAdapter(var items: List<Order>,
                   val context: Context,
                   val onViewClockListener: View.OnClickListener,
                   val onButtonClickListener: View.OnClickListener): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_card, parent, false)
        view.setOnClickListener(onViewClockListener)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = items[position]
        if(item.Ready){
            holder.llCard.setBackgroundResource(R.color.colorReady)
            holder.btnClose.visibility = View.INVISIBLE
        }
            else holder.llCard.setBackgroundResource(R.color.colorNotReady)

        holder.tvCarName.text = "${item.Mark} "+
                                "${item.Model} "+
                                "${item.Number} \n" +
                                SimpleDateFormat("dd.MM.yyyy HH:mm").format(item.Date)

        holder.btnClose.setOnClickListener(onButtonClickListener)

        val linearLayoutManager = LinearLayoutManager(context.applicationContext, LinearLayoutManager.VERTICAL, false)
        holder.rvTasks.layoutManager = linearLayoutManager
        val tasks = AppDatabase.getAppDataBase(context)?.orderWithTaskDao()?.getOrderWithTasks(item.id)?.map { it.task_id }
        val price = AppDatabase.getAppDataBase(context)?.taskDao()?.getPrice(tasks ?: emptyList()) ?: 0
        holder.tvPrice.text = context.resources.getString(R.string.Price) + price
        val adapter = TaskAdapter(tasks, context)
        holder.rvTasks.adapter = adapter
    }

    class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvCarName = itemView.tvCarName
        var rvTasks = itemView.rvTasks
        var llCard = itemView.llCard
        var btnClose = itemView.btnClose
        var tvPrice = itemView.tvPrice
    }
}