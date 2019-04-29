package com.example.carservice

import android.app.Application
import com.example.carservice.database.AppDatabase
import com.example.carservice.model.Task
import org.jetbrains.anko.doAsync
import java.security.Provider

class CarServiceApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val tasks = arrayListOf<Pair<String, Double>>(Pair<String, Double>("Замена масла", 600.0),
            Pair<String, Double>("Замена фильтров", 700.0),
            Pair<String, Double>("Замена свечей", 500.0),
            Pair<String, Double>("Покраска", 6000.0),
            Pair<String, Double>("Полировка фар", 400.0),
            Pair<String, Double>("Химчистка", 3000.0))

        doAsync {
            val database = AppDatabase.getAppDataBase(context = this@CarServiceApplication)

            if (database?.taskDao()?.getAll()!!.isEmpty()) {
                for(item in tasks) {
                    val task = Task(null, item.first, item.second)
                    database.taskDao().insertTask(task)
                }
            }
        }
    }
}