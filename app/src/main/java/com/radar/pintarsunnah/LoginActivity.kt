package com.radar.pintarsunnah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth
import com.radar.pintarsunnah.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fAuth = FirebaseAuth.getInstance()

        binding.apply {
            EtEmail.doOnTextChanged { text, start, before, count -> TILayoutEmail.error = null }
            EtPassword.doOnTextChanged { text, start, before, count -> TILayoutPassword.error = null }

            BtnLogin.setOnClickListener {
                val email = EtEmail.text.toString()
                val password = EtPassword.text.toString()

                when {
                    email.isEmpty() -> {
                        TILayoutEmail.error = "Email tidak boleh kosong"
                        EtEmail.requestFocus()
                    }
                    password.isEmpty() -> {
                        TILayoutPassword.error = "Password tidak boleh kosong"
                        EtPassword.requestFocus()
                    }
                    email.isNotEmpty() && password.isNotEmpty() -> {
                        login(email, password)
                    }
                }
            }

            TvDaftar.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    private fun login(email: String, password: String) {
        fAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    Toast.makeText(this, "Login Berhasil", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Email atau Password salah", Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()

        val result = fAuth.currentUser
        if (result != null){
            Intent(this, HomeActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}