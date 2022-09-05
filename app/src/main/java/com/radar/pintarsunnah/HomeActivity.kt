package com.radar.pintarsunnah

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.radar.pintarsunnah.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            materi.setOnClickListener {
                sharedPref = getSharedPreferences("MyShared", Context.MODE_PRIVATE)
                val namaPelajaran = TvPelajaran.text.toString()
                val editor : SharedPreferences.Editor = sharedPref.edit()
                editor.putString("pelajaran", namaPelajaran)
                editor.apply()

                startActivity(Intent(this@HomeActivity, ListPelajaranActivity::class.java))
            }

            bottomNavigation.setOnNavigationItemSelectedListener { item ->
                if (item.itemId == R.id.menuAccount){
                    startActivity(Intent(this@HomeActivity, AccountActivity::class.java))
                }
                true
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuLogout){
            if (true){
                Firebase.auth.signOut()

                Intent(this, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            startActivity(Intent(this, LoginActivity::class.java))
        }
        return true
    }
}