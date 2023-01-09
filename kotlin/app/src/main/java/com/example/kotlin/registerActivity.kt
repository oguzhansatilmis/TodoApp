package com.example.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.emailText
import kotlinx.android.synthetic.main.activity_main.registerButton
import kotlinx.android.synthetic.main.activity_register.*

class registerActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        define()
        registerButton.setOnClickListener{

            var email = emailText.text.toString()
            var password = passwordText.text.toString()
            var password2 = password2Text.text.toString()

            if(email.isEmpty() || password.isEmpty() || password2.isEmpty() )
            {
                Toast.makeText(baseContext, "Lütfen bilgileri eksiksiz doldurun.",
                    Toast.LENGTH_SHORT).show()
            }
            else
            {
                if(password.equals(password2))
                {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->

                        if(task.isSuccessful)
                        {
                            Toast.makeText(baseContext, "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
                            var intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(baseContext, "Kayıt Başarılı olmadı.Lütfen tekrar deneyiniz",
                                Toast.LENGTH_SHORT).show()
                        }

                    }
                }
                else
                {
                    Toast.makeText(baseContext, "Girdiğiniz parolalar birbirine uymamaktadır.",
                        Toast.LENGTH_SHORT).show()
                }
            }


        }
    }

    fun define()
    {

        var emailText = findViewById<EditText>(R.id.emailText)
        var passwordText = findViewById<EditText>(R.id.passwordText)
        var password2Text = findViewById<EditText>(R.id.password2Text)
        var registerButton = findViewById<Button>(R.id.registerButton)
        auth = Firebase.auth
    }

}