package com.example.lostandfound
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.lostandfound.databinding.Eachitem2Binding
import com.squareup.picasso.Picasso


class imagesAdapter2(private var mList: List<String>) :
    RecyclerView.Adapter<imagesAdapter2.ImagesViewHolder>() {

    inner class ImagesViewHolder(var binding: Eachitem2Binding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = Eachitem2Binding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        with(holder.binding){
            with(mList[position]){
                Picasso.get().load(this).into(ImageView2)
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}





