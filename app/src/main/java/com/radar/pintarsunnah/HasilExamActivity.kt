package com.radar.pintarsunnah

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.radar.pintarsunnah.databinding.ActivityHasilExamBinding
import com.radar.pintarsunnah.model.ExamModel

class HasilExamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHasilExamBinding

    private lateinit var dRef : DatabaseReference

    private lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHasilExamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("MyShared", Context.MODE_PRIVATE)
        val namaPelajaran = sharedPref.getString("pelajaran", "")

        dRef = FirebaseDatabase.getInstance().reference
        val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()

        val benar = intent.getIntExtra("benar", 0)
        val salah = intent.getIntExtra("salah", 0)
        val hasil = intent.getDoubleExtra("hasil", 0.0)

        val angkaSignifikan = 2
        val temp = Math.pow(10.0, angkaSignifikan.toDouble())
        val y = Math.round(hasil * temp).toDouble() / temp

        binding.apply {
            val pathUsername = dRef.child("data_user").child(userID).child("name").get()
            pathUsername.addOnSuccessListener { TvNamaUser.text = it.value.toString() }

            TvHasil.setText("Jawaban Benar : $benar"+"\nJawaban Salah : $salah")
            TvNilai.setText(y.toString())

            if (benar > 10) {
                BtnUlangi.setText("Cetak Sertifikat")
                TvLulus.setText("Selamat anda lulus silahkan klik cetak sertifikat")

                BtnUlangi.setOnClickListener {
                    val semuaPesan = "Jawaban Benar : $benar" + "\nJawaban Salah : $salah" + "\nNilai : $y" + "\nSaya ingin mencetak sertifikat"
                    val kirimWA = Intent(Intent.ACTION_SEND)
                    kirimWA.setType("text/plain")
                    kirimWA.putExtra(Intent.EXTRA_TEXT, semuaPesan)
                    kirimWA.putExtra("jid", "6285893473241" + "@s.whatsapp.net")
                    kirimWA.setPackage("com.whatsapp")
                    startActivity(kirimWA)

                    dRef.child("data_sertifikat").child(userID).child(namaPelajaran.toString())
                        .setValue(ExamModel(benar.toString(), hasil.toString(), namaPelajaran, userID))
                }
            } else {
                BtnUlangi.setText("Ulangi Exam")
                TvLulus.setText("Belum lulus silahkan ulangi lagi")
                BtnUlangi.setOnClickListener {
                    finish()
                    startActivity(Intent(this@HasilExamActivity, TestExamActivity::class.java))
                }
            }
        }
    }
}