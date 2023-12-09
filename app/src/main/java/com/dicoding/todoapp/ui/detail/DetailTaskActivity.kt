package com.dicoding.todoapp.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.ui.list.TaskActivity
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action OK
        val title: TextView = findViewById(R.id.detail_ed_title)
        val description: TextView = findViewById(R.id.detail_ed_description)
        val dueDate: TextView = findViewById(R.id.detail_ed_due_date)
        val taskId = intent.getIntExtra(TASK_ID, 0)

        val factory = ViewModelFactory.getInstance(this)
        detailTaskViewModel = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]
        detailTaskViewModel.setTaskId(taskId)

        detailTaskViewModel.task.observe(this) {
            it?.let {
                title.text = it.title
                description.text = it.description
                dueDate.text = DateConverter.convertMillisToString(it.dueDateMillis)
            }
        }

        findViewById<Button>(R.id.btn_delete_task).setOnClickListener {
            detailTaskViewModel.deleteTask()
            finish()
        }
    }

}