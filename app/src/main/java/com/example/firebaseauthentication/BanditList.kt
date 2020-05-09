package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_bandit_list.*

class BanditList : AppCompatActivity() {

// retrieve the database instance for logging out
    val myAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bandit_list)


        // manually created list to fit into the cards of the recycler view
        val bandits = listOf(
            BanditDetail("","Mukosi Mwenza", "He was infamous for robbery with violence"),
            BanditDetail("","Mukosi Mwenza", "He was infamous for robbery with violence"),
            BanditDetail("","Mukosi Mwenza", "He was infamous for robbery with violence"),
            BanditDetail("","Mukosi Mwenza", "He was infamous for robbery with violence"),
            BanditDetail("","Mukosi Mwenza", "He was infamous for robbery with violence"),
            BanditDetail("","Mukosi Mwenza", "He was infamous for robbery with violence")

        )

        myRecycler.layoutManager = LinearLayoutManager(this)
        myRecycler.adapter = BanditAdapter(bandits)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.signOut) {
            myAuth.signOut()
            Toast.makeText(this, "You have been signed out", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }


}
