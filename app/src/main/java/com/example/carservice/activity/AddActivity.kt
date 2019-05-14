package com.example.carservice.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androidbuts.multispinnerfilter.KeyPairBoolData
import com.androidbuts.multispinnerfilter.SpinnerListener
import com.example.carservice.R
import com.example.carservice.database.AppDatabase
import com.example.carservice.model.Order
import com.example.carservice.model.OrderWithTask
import kotlinx.android.synthetic.main.activity_add.*
import org.jetbrains.anko.doAsync
import java.util.*

class AddActivity: AppCompatActivity(), SpinnerListener {

    private var tasks = emptyList<KeyPairBoolData>()
    private var selected = emptyList<KeyPairBoolData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        spinnerTasks.isClickable = false
        doAsync {
            val db = AppDatabase.getAppDataBase(this@AddActivity)

            val listTask = db?.taskDao()?.getAll()

            tasks = listTask?.map {
                KeyPairBoolData().apply {
                    id = it.id!!
                    name = it.name
                }
            } ?: emptyList()

            spinnerTasks.setItems(tasks, -1, this@AddActivity)
            spinnerTasks.isClickable = true
        }

        btnAddToList.setOnClickListener { addOrder() }
    }

    override fun onItemsSelected(p0: MutableList<KeyPairBoolData>?) {
        // Обновление списка выбраных Task
        if (p0 != null) {
            selected = p0
        }
    }

    fun addOrder() {
        val db = AppDatabase.getAppDataBase(this@AddActivity)
        val count = db?.orderDao()?.getAll()?.count() ?: 0
        val id = (count + 1).toLong()
        db?.orderDao()?.insertOrder(
            Order(
                id,
                etVIN.text.toString(),
                etMark.text.toString(),
                etModel.text.toString(),
                etNumber.text.toString(),
                Calendar.getInstance().time,
                false,
                etInfo.text.toString()
            )
        )
        for(it in selected){
            if(it.isSelected) db?.orderWithTaskDao()?.insert(OrderWithTask(id, it.id))
        }

        this@AddActivity.finish()
    }
}