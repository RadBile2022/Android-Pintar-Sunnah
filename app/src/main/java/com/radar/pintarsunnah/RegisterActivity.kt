package com.radar.pintarsunnah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.radar.pintarsunnah.databinding.ActivityRegisterBinding
import com.radar.pintarsunnah.model.UserModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var fAuth : FirebaseAuth
    private lateinit var dRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fAuth = FirebaseAuth.getInstance()
        dRef = FirebaseDatabase.getInstance().reference
        // menghilangkan error pada textfield ketka udah di isi
        binding.apply {
            EtNama.doOnTextChanged { text, start, before, count -> TILayoutNama.error = null }
            EtEmailDaftar.doOnTextChanged { text, start, before, count -> TILayoutEmailDaftar.error = null }
            EtPasswordDaftar.doOnTextChanged { text, start, before, count -> TILayoutPasswordDaftar.error = null }
            EtPasswordUlang.doOnTextChanged { text, start, before, count -> TILayoutPasswordUlang.error = null }

            BtnDaftar.setOnClickListener {
                val nama = EtNama.text.toString()
                val email = EtEmailDaftar.text.toString()
                val password = EtPasswordDaftar.text.toString()
                val ulangiPassword = EtPasswordUlang.text.toString()

                when {
                    nama.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && ulangiPassword.isNotEmpty() -> {
                        if (password != ulangiPassword){
                            Toast.makeText(this@RegisterActivity, "Password tidak sama", Toast.LENGTH_LONG).show()
                        } else {
                            daftar(email, password)
                        }
                    }
                    nama.isEmpty() -> {
                        TILayoutNama.error = "Nama tidak boleh kosong"
                        EtNama.requestFocus()
                    }
                    email.isEmpty() -> {
                        TILayoutEmailDaftar.error = "Email tidak boleh kosong"
                        EtEmailDaftar.requestFocus()
                    }
                    password.isEmpty() -> {
                        TILayoutPasswordDaftar.error = "Password tidak boleh kosong"
                        EtPasswordDaftar.requestFocus()
                    }
                    ulangiPassword.isEmpty() -> {
                        TILayoutPasswordUlang.error = "Password tidak boleh kosong"
                        EtPasswordUlang.requestFocus()
                    }
                }
            }
            TvLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }

    private fun dataUser(){
        var email = binding.EtEmailDaftar.text.toString()
        var nama = binding.EtNama.text.toString()
        var uuid = fAuth.uid.toString()
        // realtime database
        dRef.child("data_user").child(uuid).setValue(UserModel(email, nama, uuid))

        if (true){
            Firebase.auth.signOut()
        }
    }

    private fun daftar(email : String, password : String){
        //authtentifikasi
        fAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    dataUser()
                    binding.EtNama.text!!.clear()
                    binding.EtEmailDaftar.text!!.clear()
                    binding.EtPasswordDaftar.text!!.clear()
                    binding.EtPasswordUlang.text!!.clear()
                    Toast.makeText(this, "Daftar Berhasil", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Daftar Gagal", Toast.LENGTH_LONG).show()
                }
            }
    }
}