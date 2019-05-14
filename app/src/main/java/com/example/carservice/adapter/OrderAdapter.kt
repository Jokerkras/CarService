package com.example.carservice.adapter

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carservice.R
import com.example.carservice.database.AppDatabase
import com.example.carservice.model.Order
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.order_card.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.backgroundResource
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

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
                                item.Date.toString()

        holder.btnClose.setOnClickListener(onButtonClickListener)

        val linearLayoutManager = LinearLayoutManager(context.applicationContext, LinearLayoutManager.VERTICAL, false)
        holder.rvTasks.layoutManager = linearLayoutManager
        val tasks = AppDatabase.getAppDataBase(context)?.orderWithTaskDao()?.getOrderWithTasks(item.id)?.map { it.task_id }
        val adapter = TaskAdapter(tasks, context)
        holder.rvTasks.adapter = adapter
    }

    class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvCarName = itemView.tvCarName
        var rvTasks = itemView.rvTasks
        var llCard = itemView.llCard
        var btnClose = itemView.btnClose
    }
}