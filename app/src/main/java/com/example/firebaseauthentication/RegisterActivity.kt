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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

//    connect to the database
    val myAuth = FirebaseAuth.getInstance()

//    *** Database reference
    lateinit var myUsers : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        btnReg.setOnClickListener {
            // initiate registration
            View -> Register()
        }
    }

    private fun Register(){
//      initialise a progress bar
        var progress = ProgressDialog(this)
        progress.setTitle("Registration")
        progress.setMessage("Please wait....")

//   ***     create the database to hold the user name
        myUsers = FirebaseDatabase.getInstance().getReference("Names")


        val nameTxt = findViewById<View>(R.id.regName) as EditText
        val emailTxt = findViewById<View>(R.id.regMail) as EditText
        val passwordTxt = findViewById<View>(R.id.regPass) as EditText
        val pcTxt = findViewById<View>(R.id.regPC) as EditText

        var name = nameTxt.text.toString()
        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        var passconfirm = pcTxt.text.toString()


        if (name.isEmpty() or email.isEmpty() or password.isEmpty() or passconfirm.isEmpty()){
            Toast.makeText(this, "Please fill out all inputs", Toast.LENGTH_LONG).show()
        } else{
            // check that the passwords match
            if (password != passconfirm){
//                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_LONG).show()
                dialogbox("Password Error", "Passwords don't match!")
            } else{

                progress.show()
//            create user in database
                myAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    progress.dismiss()
                    if (task.isSuccessful){

//                     *** add the info to the database using the uid as a reference id
                        val user = myAuth.currentUser
                        val uid = user!!.uid
                        myUsers.child(uid).child("Name").setValue(name)

                        Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, MainActivity:: class.java))
                    } else{
                        Toast.makeText(this, "Error with registration", Toast.LENGTH_LONG).show()
                    }
                })
            }
            // create user


        }

    }
    private fun dialogbox(title: String, message: String){
        var mydialog = AlertDialog.Builder(this)
        mydialog.setTitle(title)
        mydialog.setMessage(message)
        mydialog.setCancelable(false)
        mydialog.setPositiveButton("Ok", DialogInterface.OnClickListener{ dialog, which ->
            dialog.dismiss()
        })
        mydialog.create().show()
    }
}
