package com.example.e_posyandu.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.e_posyandu.databinding.ItemBumilRestiBinding
import com.example.e_posyandu.models.IbuHamilResikoTinggi
import java.time.format.DateTimeFormatter

class RestiBumilAdapter(private var restiBumil : List<IbuHamilResikoTinggi>, private var context : Context) : RecyclerView.Adapter<RestiBumilAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemBumilRestiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemBumilRestiBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tanggal = restiBumil[position].created_at
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyy")

        holder.binding.tvTanggal.text = tanggal!!.format(formatter)
    }

    override fun getItemCount() = restiBumil.size
}