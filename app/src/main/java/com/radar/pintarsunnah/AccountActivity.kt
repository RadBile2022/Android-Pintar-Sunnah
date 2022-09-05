package com.radar.pintarsunnah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.radar.pintarsunnah.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAccountBinding
    private lateinit var dRef : DatabaseReference
    private lateinit var fAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Account"

        dRef = FirebaseDatabase.getInstance().reference
        fAuth = FirebaseAuth.getInstance()

        val uidUser = fAuth.currentUser?.uid.toString()

        val pathUsername = dRef.child("data_user").child(uidUser).child("name").get()
        val pathEmail = dRef.child("data_user").child(uidUser).child("email").get()

        pathUsername.addOnSuccessListener { binding.TvNamaUser.text = it.value.toString() }
        pathEmail.addOnSuccessListener { binding.TvEmailUser.text = it.value.toString() }
    }
}