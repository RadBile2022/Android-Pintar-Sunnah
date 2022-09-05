package com.radar.pintarsunnah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.radar.pintarsunnah.adapter.PelajaranAdapter
import com.radar.pintarsunnah.databinding.ActivityListPelajaranBinding
import com.radar.pintarsunnah.model.PelajaranModel

class ListPelajaranActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListPelajaranBinding
    private lateinit var listPelajaran : ArrayList<PelajaranModel>
    private lateinit var myAdapter : PelajaranAdapter

    private lateinit var dRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListPelajaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listPelajaran = arrayListOf()
        dRef = FirebaseDatabase.getInstance().reference

        myAdapter = PelajaranAdapter(listPelajaran)

        binding.RvListPelajaran.adapter = myAdapter
        binding.RvListPelajaran.layoutManager = LinearLayoutManager(this)
        binding.RvListPelajaran.setHasFixedSize(true)

        getListData()

        binding.finalExam.setOnClickListener {
            startActivity(Intent(this, TestExamActivity::class.java))
        }
    }

    private fun getListData() {
        dRef.child("data_pelajaran").child("Dasar Islam").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (listUserSnapshot in snapshot.children) {
                        binding.progressBar.visibility = View.GONE
                        val listUser = listUserSnapshot.getValue(PelajaranModel::class.java)
                        listPelajaran.add(listUser!!)
                    }
                    myAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}