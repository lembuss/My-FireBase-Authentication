package com.example.firebaseauthentication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class LandingPage : AppCompatActivity() {

    //        *** Database instance and reference
        lateinit var myUsers : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        //        refer to welcome text
        val displaytxt = findViewById<View>(R.id.welcomeText) as TextView

//        target the information from database
        myUsers = FirebaseDatabase.getInstance().getReference("Names")
        var user = FirebaseAuth.getInstance().currentUser
        var uid = user!!.uid

        myUsers.child(uid).child("Name").addValueEventListener( object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                // import user name and display it on landing page
                val result = snapshot.value.toString()
                displaytxt.text = "Welcome $result"
            }

        })

        Handler().postDelayed({
            startActivity(Intent(this, BanditList::class.java))
            finish()
        },2000)
    }
}
