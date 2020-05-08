package com.example.firebaseauthentication

import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // define database connection
    val myAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            // initiate log in process
            View -> logIn()
        }

        logReg.setOnClickListener {
            // direct to the register page
            startActivity(Intent(this, RegisterActivity:: class.java))
        }
    }

    private fun logIn(){
        val progress = ProgressDialog(this)
        progress.setTitle("Logging in")
        progress.setMessage("Please wait....")

        // retrieve data from layout activity
        val emailTxt = findViewById<View>(R.id.editMail) as EditText
        val passwordTxt = findViewById<View>(R.id.editPassword) as EditText

        // extract the email and password as strings
        val email = emailTxt.text.toString()
        val password = passwordTxt.text.toString()

        // check for filled inputs
        if(email.isEmpty() or password.isEmpty()){
            // display warning text
            Toast.makeText(this, "Please fill out all the inputs", Toast.LENGTH_LONG).show()
        } else{
            // execute log in
            progress.show()
            myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful){
                    progress.dismiss()
                    // confirm log in and redirect
                    Toast.makeText(this, "Log in Successful", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, BanditList::class.java))
                } else{
//                    Toast.makeText(this, "Log In Error, try again", Toast.LENGTH_LONG).show()
                    dialogBox("Log In Error", "Wrong credentials. Please try again.")
                    // clear all fields
                    emailTxt.setText(null)
                    passwordTxt.setText(null)
                }
            })
        }

    }

    private fun dialogBox(title: String, message: String){
        val mydialog = AlertDialog.Builder(this)
        mydialog.setTitle(title)
        mydialog.setMessage(message)
        mydialog.setCancelable(false)
        mydialog.setPositiveButton("Ok", DialogInterface.OnClickListener{ dialog, which ->
            dialog.dismiss()
        })
        mydialog.create().show()
    }

}
