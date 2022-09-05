package com.radar.pintarsunnah

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.radar.pintarsunnah.databinding.ActivityTestExamBinding

class TestExamActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityTestExamBinding

    private var nomor = 0

    private var hasil = 0.0
    private var benar = 0
    private var salah = 0

    private val pertanyaan = arrayOf(
        "1. Pada pelajaran ini topik bahasan dari hadis arbain nawawi ke ?",
        "2. Hadis ini terkenal dengan sebutan ?",
        "3. Dalam hadis ini ada tiga pokok bahasan paling penting dalam agama adalah ?",
        "4. Urgensi hadis ini dibahas karena ?",
        "5. Imam ibn rajab memberikan penjelasan bahwa hadis ini agung karena ?",
        "6. Hadis ini diriwayatkan oleh sahabat ?",
        "7. Faidah yang didapatkan dari hadis ini adalah ?",
        "8. Pentingya seseorang berilmu maka harus ?",
        "9. Islam adalah ?",
        "10. Landasan islam ada 5 yaitu ?",
        "11. Makna Syahadat yang paling benar adalah ?",
        "12. Komponen Syahadat selain mentauhidkan Allah juga ?",
        "13. Rukun iman itu ada apa saja?",
        "14. Iman kepada Allah dengan cara mentauhidkan ?",
        "15. Seorang Ahlu sunnah meyakini hakikat iman itu ?"
    )

    private val pilihan = arrayOf(
        "Satu", "Dua", "Tiga", "Empat",
        "Hadis Ahad", "Hadis Jibril", "Hadis Nabi", "Hadis Mutawatir",
        "Adab, Rukun islam, Rukun ihsan", "Rukun islam, Rukun iman, Tanda kiamat", "Rukun islam, Rukun Iman, Rukun Ihsan", "Rukun Islam, Rukun Adab, Rukun Ikhlas",
        "Difatwakan oleh para ulama", "Dinukilkan oleh imam Nawawi", "Tercantum dalam hadis arbain Nawawi", "Merupakan pondasi dari agama seseorang",
        "Mencakup rukun islam, iman, dan ihsan", "Hadis ini sangat cocok untuk meningkatkan keimanan", "Menghafalkan hadis ini merupakan faidah terbesar dan agung", "Selain adab kita juga harus berakhlak dalam menuntut ilmu",
        "Amr bin khattab", "Ka’ab bin malik", "Ibnu Umar bin Khattab", "Umar bin Khattab",
        "Pentingnya majelis ilmu dan dzikir", "Pentingnya ilmu dan amal", "Pentingnya majlis ilmu dan adab", "Pentingnya tarbiyah",
        "Mendatangi majlis ilmu tiap hari dan giat serta menargetkan khatam sehari", "Belajar tanpa mengenal waktu", "Membuat catatan faidah penting", "Menghadiri majlis ilmu secara rutin berkala agar tidak bosan",
        "Tunduk dan patuh kepada Allah dengan cara mentauhidkan, mentaati dan membebaskan diri dari kemusyrikan dan ahli syirik.", "Tunduk dan patuh kepada Allah dengan cara beribadah dengan baik serta tidak berdoa kepada nabi.", "Tunduk dan patuh kepada Allah dengan cara melakukan syahadat, sholat, zakat, puasa dan haji.", "Tunduk terhadap aturan syariat dan tidak melakukan kemaksiatan sama sekali selama hidup didunia.",
        "Shalat, zakat, puasa, infaq, baiat", "Baiat, ijtihad, nasehat, sami’na, wa atho’na", "Syahadat, Shalat, Zakat, Puasa dan Haji bila mampu", "Dzikir, shalawat, pujian, nariyah, mut’ah",
        "Tiada tuhan selain Allah dan Nabi Muhammad utusan Allah", "Tiada pencipta selain Allah", "Tiada sesembahan yang patut disembah selain Allah", "Tiada pemberi rezeki selain Allah",
        "Mengikuti nabi dengan ittiba’ tanpa menambah ataupun mengurangi syariat", "Berhak memodifikasi amalan ibadah meskipun belum pernah dicontohkan nabi", "Ibadah boleh dibuat sendiri yang penting niatnya baik", "Ibadah boleh diubah sesuai perkembangan zaman",
        "Iman kepada Allah, malaikat, zakat, puasa,nabi, kitab", "Iman kepada Allah, malaikat, kitab, nabi, hari akhir, takdir", "Iman kepada Allah, hari akhir, takdir, kitab, iman, islam", "Iman kepada malaikat, wali, imam, Allah, Rasul",
        "Rububiyah, Uluhiyah, asma’ wa shifat", "Rububiyah, Uluhiyah", "Uluhiyah, asma’ wa shifat", "Rububiyah saja",
        "Apabila melakukan maksiat maka dia kafir dan kekal dineraka selamanya", "Pelaku maksiat tidak mengurangi keimanan sehingga dirinya tidak berdosa", "Iman bertambah dengan ketakwaan dan berkurang dengan maksiat", "Iman bisa dicapai apabila sudah mencapai derajat wali dan ulama yang muktabar"
    )

    private val jawaban = arrayOf(
        "Dua",
        "Hadis Jibril",
        "Rukun islam, Rukun Iman, Rukun Ihsan",
        "Merupakan pondasi dari agama seseorang",
        "Mencakup rukun islam, iman, dan ihsan",
        "Umar bin Khattab",
        "Pentingnya majlis ilmu dan adab",
        "Menghadiri majlis ilmu secara rutin berkala agar tidak bosan",
        "Tunduk dan patuh kepada Allah dengan cara mentauhidkan, mentaati dan membebaskan diri dari kemusyrikan dan ahli syirik.",
        "Syahadat, Shalat, Zakat, Puasa dan Haji bila mampu",
        "Tiada sesembahan yang patut disembah selain Allah",
        "Mengikuti nabi dengan ittiba’ tanpa menambah ataupun mengurangi syariat",
        "Iman kepada Allah, malaikat, kitab, nabi, hari akhir, takdir",
        "Rububiyah, Uluhiyah, asma’ wa shifat",
        "Iman bertambah dengan ketakwaan dan berkurang dengan maksiat"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestExamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            TvPertanyaan.setText(pertanyaan[nomor])
            RbA.setText(pilihan[0])
            RbB.setText(pilihan[1])
            RbC.setText(pilihan[2])
            RbD.setText(pilihan[3])

            RbPilihan.check(0)
            benar = 0
            salah = 0

            BtnNext.setOnClickListener {
                if (RbA.isChecked || RbB.isChecked || RbC.isChecked || RbD.isChecked){
                    val jawabanUser = findViewById(RbPilihan.getCheckedRadioButtonId()) as RadioButton
                    val ambilJawaban = jawabanUser.text.toString()

                    RbPilihan.check(0)

                    if (ambilJawaban.equals(jawaban[nomor])){
                        benar++
                    } else {
                        salah++
                    }

                    nomor++ // next soal
                    if (nomor < pertanyaan.size){
                        TvPertanyaan.setText(pertanyaan[nomor])
                        RbA.setText(pilihan[(nomor * 4) + 0])
                        RbB.setText(pilihan[(nomor * 4) + 1])
                        RbC.setText(pilihan[(nomor * 4) + 2])
                        RbD.setText(pilihan[(nomor * 4) + 3])
                    } else {
                    // nilai
                        hasil = (benar * 6.6) + 1

                        val intent = Intent(this@TestExamActivity, HasilExamActivity::class.java)
                        intent.putExtra("hasil", hasil)
                        intent.putExtra("benar", benar)
                        intent.putExtra("salah", salah)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this@TestExamActivity, "Tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}