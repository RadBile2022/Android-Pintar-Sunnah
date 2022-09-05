package com.radar.pintarsunnah.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.radar.pintarsunnah.PelajaranActivity
import com.radar.pintarsunnah.R
import com.radar.pintarsunnah.model.PelajaranModel

class PelajaranAdapter(val list: ArrayList<PelajaranModel>) : RecyclerView.Adapter<PelajaranAdapter.Pelajaran>() {

    class Pelajaran (itemView: View) : RecyclerView.ViewHolder(itemView){
        var tv_JudulBab = itemView.findViewById<TextView>(R.id.Tv_JudulBab)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pelajaran {
        return Pelajaran(LayoutInflater.from(parent.context).inflate(R.layout.list_pembelajaran, parent, false))
    }

    override fun onBindViewHolder(holder: Pelajaran, position: Int) {
        val result = list[position]

        holder.tv_JudulBab.text = result.judul

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PelajaranActivity::class.java)
            intent.apply {
                putExtra("judul", result.judul)
                putExtra("detail", result.detail)
                putExtra("gambar", result.gambar)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}