package com.example.recycleviews

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var emailView: EditText
    lateinit var passwordView: EditText

    //lateinit var db: FirebaseFirestore




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        getSupportActionBar()?.setTitle("Our renters favorite Spots");
        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(getResources().getColor(R.color.black)));

        auth = Firebase.auth
        emailView = findViewById((R.id.email))
        passwordView = findViewById(R.id.password)


        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            signUp()

        }

        val signInButton = findViewById<Button>(R.id.signInButton)
        signInButton.setOnClickListener {
            signIn()

        }

        // val signInButton=findViewById<Button>(R.id.signInButton)



    }

    fun goToVanListActivity() {
        val intent = Intent ( this, Vans::class.java)
        startActivity(intent)
    }

    fun signIn() {
        val email = emailView.text.toString()
        val password = passwordView.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            return
        }
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("!!!", "Sucess")
                    goToVanListActivity()
                } else {
                    Log.d("!!!", "not${task.exception}")
                }
            }
    }

        fun signUp() {
            val email = emailView.text.toString()
            val password = passwordView.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                return
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("!!!", "Sucess")
                        goToVanListActivity()
                    } else {
                        Log.d("!!!", "not${task.exception}")
                    }
                }
        }
    }
