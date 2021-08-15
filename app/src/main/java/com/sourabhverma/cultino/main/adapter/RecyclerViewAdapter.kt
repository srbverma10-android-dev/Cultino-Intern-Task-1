package com.sourabhverma.cultino.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sourabhverma.cultino.databinding.RecyclerViewBinding
import com.sourabhverma.cultino.main.pojo.OtherMandi

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private lateinit var items : List<OtherMandi>
    fun setDataList(data: List<OtherMandi>){
        this.items = data
    }

    class MyViewHolder(private val binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : OtherMandi){
            binding.Name.text = data.hindi_name
            binding.Address.text = data.district.plus(" ,").plus(data.state).plus(" (")
                .plus(data.km).plus(")")
            binding.LastSeen.text = data.last_date
            Glide.with(binding.Image)
                .load(data.image)
                .into(binding.Image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerViewBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


}