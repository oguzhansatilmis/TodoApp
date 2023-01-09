package com.example.kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var database = FirebaseDatabase.getInstance()
        // var myRef = database.reference
        val myRef = database.getReference("kullanıcılar")
        var email = emailText.text.toString()
        var urun = email
        // myRef.child("urunler")

        // myRef.setValue("Hello, World222!")

        define()

        loginButton.setOnClickListener()
        {
            var email = emailText.text.toString()
            var parola = parolaText.text.toString()
            auth.signInWithEmailAndPassword(email,parola).addOnCompleteListener(this)
            {task ->
                if(task.isSuccessful)
                {
                    var intent = Intent(this,homePage::class.java)
                    startActivity(intent)
                   // intent.putExtra("useriD",userİd) sonra lazım olucak


                }
                else
                {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }

            }
        }

        registerButton.setOnClickListener()
        {
            var intent = Intent(this,registerActivity::class.java)
            startActivity(intent)
        }

    }

    fun define()
    {
        var emailText = findViewById<EditText>(R.id.emailText)
        var paraloText = findViewById<EditText>(R.id.parolaText)
        var loginButton = findViewById<Button>(R.id.loginButton)
        var registerButton = findViewById<Button>(R.id.registerButton)
        auth = Firebase.auth


    }
}