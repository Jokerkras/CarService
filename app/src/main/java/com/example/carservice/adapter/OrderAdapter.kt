package com.example.carservice.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carservice.R
import com.example.carservice.model.Order
import kotlinx.android.synthetic.main.order_card.view.*

class OrderAdapter(var items: List<Order>,
                   val context: Context,
                   val onClockListener: View.OnClickListener): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_card, parent, false)
        view.setOnClickListener(onClockListener)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = items.get(position)
        holder.tvCarName.text = "${item.Mark} "+
                                "${item.Model} "+
                                "${item.Number} " +
                                "${item.Date.day}.${item.Date.month}.${item.Date.year} ${item.Date.hours}:${item.Date.minutes}"
    }

    class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvCarName = itemView.tvCarName
        var rvTasks = itemView.rvTasks
    }
}