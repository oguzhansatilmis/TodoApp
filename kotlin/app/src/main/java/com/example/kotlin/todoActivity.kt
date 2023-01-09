package com.example.kotlin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.grpc.internal.DnsNameResolver.SrvRecord
import kotlinx.android.synthetic.main.activity_todo.*

class todoActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth
    val  todoArrayListText = arrayListOf<String>()
    val  todoArrayListDate = arrayListOf<String>()
    val  todoArrayListId = arrayListOf<String>()
    val  todoArrayDetail = arrayListOf<String>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        getData()

        // adapter
        buttontodo
        var button3 = findViewById<Button>(R.id.button3)
        var buttontodo = findViewById<Button>(R.id.buttontodo)
        button3.setOnClickListener()
        {
            val layoutManager =LinearLayoutManager(this)
            recyclerView.layoutManager = layoutManager

            val adapter = RecyclerAdapter(this,todoArrayListText,todoArrayListId,todoArrayListDate,todoArrayDetail)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        buttontodo.setOnClickListener{
            var intent = Intent(this,homePage::class.java)
            startActivity(intent)
        }

    }

    fun getData()
    {
        auth = Firebase.auth
        val user = Firebase.auth.currentUser
        val uid = user?.uid

        db.collection("${uid}").addSnapshotListener { snapshot, error ->
            if(error != null){
                Toast.makeText(baseContext, "hata.",
                    Toast.LENGTH_SHORT).show()
            }
            else{
                if (snapshot != null){
                    if (!snapshot.isEmpty)
                    {
                        val documents = snapshot.documents

                        for(document in documents ){
                            var documentid = document.id
                            var todogetText = document.get("todoText").toString()
                            var todogetDate = document.get("date").toString()
                            var todogetDetail = document.get("detail").toString()


                            todoArrayListText.add(todogetText).toString()
                            todoArrayListDate.add(todogetDate).toString()
                            todoArrayListId.add(documentid).toString()
                            todoArrayDetail.add(todogetDetail).toString()


                        }
                    }
                }
            }
        }
    }


}