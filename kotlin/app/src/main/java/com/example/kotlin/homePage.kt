package com.example.kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.Timestamp.now
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.Date
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant.now
import java.time.LocalDate


import java.time.LocalDateTime.now

class homePage : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        define()
        todoGitButton.setOnClickListener()
        {
            var intent = Intent(this,todoActivity::class.java)
            startActivity(intent)
        }

        todoAdd.setOnClickListener{
            auth = Firebase.auth
            val user = Firebase.auth.currentUser
            val uid = user?.uid

            var valuetodoText = todoText.text.toString()
            var valueTextDetailsAdd = todoTextDetailsAdd.text.toString()

            if(valuetodoText.isEmpty() || valueTextDetailsAdd.isEmpty())
            {
                Toast.makeText(baseContext, "Todo bilgileri eksik.",
                    Toast.LENGTH_SHORT).show()
            }
            else{
                val sdf = SimpleDateFormat("dd/M/yyyy")
                val currentDate = sdf.format(java.util.Date())
                val todoMap = hashMapOf<String,Any>()
                todoMap.put("todoText",valuetodoText)
                todoMap.put("date",currentDate)
                todoMap.put("detail",valueTextDetailsAdd)
                db.collection("${uid}").add(todoMap).addOnCompleteListener{ task->
                    if(task.isSuccessful)
                    {
                        Toast.makeText(baseContext, "Todo Eklendi.",
                            Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener{exception ->
                    Toast.makeText(baseContext, "Todo Eklenemedi!!!.",
                        Toast.LENGTH_SHORT).show()
                }
                var intent = Intent(this,todoActivity::class.java)
                startActivity(intent)
            }

        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun define()
    {
        var todoText = findViewById<EditText>(R.id.todoText)
        var todoAdd= findViewById<Button>(R.id.todoAdd)
        var todoGitButton= findViewById<Button>(R.id.todoGitButton)
        var todoTextDetailsAdd = findViewById<EditText>(R.id.todoTextDetailsAdd)

    }

}