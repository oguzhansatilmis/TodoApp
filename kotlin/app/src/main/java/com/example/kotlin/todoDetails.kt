package com.example.kotlin

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_todo.*
import kotlinx.android.synthetic.main.activity_todo_details.*
import org.w3c.dom.Text

class todoDetails : AppCompatActivity() {
    val db = Firebase.firestore


    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_details)
        var gelenTodoText = intent.getStringExtra("todoText")
        var gelenTodoId = intent.getStringExtra("todoId")
        var gelenTodoDate = intent.getStringExtra("todoDate")
        var gelenTodoDetails = intent.getStringExtra("todoDetail")

        var textDetails = findViewById<TextView>(R.id.textDetails)
        var textTodoDeatils = findViewById<TextView>(R.id.textTodoDeatils)
        var textTodoDate = findViewById<TextView>(R.id.textTodoDate)
        var todoDelete = findViewById<Button>(R.id.todoDelete)
        var todogeriGit = findViewById<Button>(R.id.todogeriGit)

            textDetails.setText(gelenTodoText)
            textTodoDate.setText(gelenTodoDate + "tarihinde oluşturuldu.")
            textTodoDeatils.setText(gelenTodoDetails)

            todoDelete.setOnClickListener{
            deleteTodo()
                var intent = Intent(this,homePage::class.java)
                startActivity(intent)
        }
        todogeriGit.setOnClickListener{
            var intent = Intent(this,homePage::class.java)
            startActivity(intent)
        }
        }
    fun deleteTodo()
    {
        var gelenTodoId = intent.getStringExtra("todoId")
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        val uid = user?.uid
        db.collection("${uid}").document("${gelenTodoId}")
            .delete()
            .addOnSuccessListener { Log.d(TAG, "Todo silindi")

            }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }


    }
