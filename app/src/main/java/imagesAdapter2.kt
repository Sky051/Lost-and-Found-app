package com.example.lostandfound
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.lostandfound.databinding.Eachitem2Binding
import com.squareup.picasso.Picasso


class imagesAdapter2(private var mList: List<String>,private var nList: List<String>,private var oList:List<String>) :
    RecyclerView.Adapter<imagesAdapter2.ImagesViewHolder>() {

    inner class ImagesViewHolder(var binding: Eachitem2Binding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = Eachitem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return mList.size
    }
    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        with(holder.binding) {
            with(mList[position]) {
                Picasso.get().load(this).into(ImageView)
            }
            with(nList[position]) {
                textView.setText(this)
            }
            with(oList[position]) {
                textView1.setText(this)
            }
        }


    }
}





