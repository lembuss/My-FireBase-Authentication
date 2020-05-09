package com.example.firebaseauthentication

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

//        target the same database
        myUsers = FirebaseDatabase.getInstance().getReference("Names")

        myUsers.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                // import user name and display it on landing page
                val result = snapshot.child("Name").toString()
                displaytxt.text = "Welcome" + result
            }

        })

        Handler().postDelayed({
            startActivity(Intent(this, BanditList::class.java))
            finish()
        },2000)
    }
}
