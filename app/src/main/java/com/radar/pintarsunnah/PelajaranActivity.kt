package com.radar.pintarsunnah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.radar.pintarsunnah.databinding.ActivityPelajaranBinding

class PelajaranActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPelajaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPelajaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val judul = intent.getStringExtra("judul")
        val detail = intent.getStringExtra("detail")
        val gambar = intent.getStringExtra("gambar")

        supportActionBar!!.title = judul

        Glide.with(this@PelajaranActivity).load(gambar)
            .apply(RequestOptions().override(700, 6000))
            .error(R.drawable.logo)
            .into(binding.ImgPelajaran)

        Glide.with(this@PelajaranActivity).load(detail)
            .apply(RequestOptions().override(800, 4000))
            .error(R.drawable.logo)
            .into(binding.ImgDetail)

        binding.apply {
            BtnSelesai.setOnClickListener {
                startActivity(Intent(this@PelajaranActivity, ListPelajaranActivity::class.java))
            }
        }
    }
}