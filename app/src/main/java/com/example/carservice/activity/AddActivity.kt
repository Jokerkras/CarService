package com.example.carservice.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.androidbuts.multispinnerfilter.KeyPairBoolData
import com.androidbuts.multispinnerfilter.SpinnerListener
import com.example.carservice.R
import com.example.carservice.database.AppDatabase
import com.example.carservice.model.Task
import kotlinx.android.synthetic.main.activity_add.*
import org.jetbrains.anko.doAsync

class AddActivity: AppCompatActivity(), SpinnerListener {

    private var tasks = emptyList<KeyPairBoolData>()
    private var selectedTasks = emptyList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        spinnerTasks.isClickable = false
        doAsync {
            val db = AppDatabase.getAppDataBase(this@AddActivity)

            val listTask = db?.taskDao()?.getAll()

            tasks = listTask?.map { KeyPairBoolData().apply {
                id = it.id!!
                name = it.name
            } } ?: emptyList()

            spinnerTasks.setItems(tasks, -1, this@AddActivity)
            spinnerTasks.isClickable = true
        }
    }

    override fun onItemsSelected(p0: MutableList<KeyPairBoolData>?) {

    }
}