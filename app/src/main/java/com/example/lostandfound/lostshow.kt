package com.example.lostandfound

import android.annotation.SuppressLint
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostandfound.databinding.ActivityLostshowBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class lostshow : AppCompatActivity() {
    private lateinit var binding: ActivityLostshowBinding

    private lateinit var firebaseFirestore : FirebaseFirestore
    private var mList = mutableListOf<String>()
    private lateinit var adapterLost: imagesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLostshowBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initVars()
        getImages()
    }


    private fun initVars(){

        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapterLost= imagesAdapter(mList)
        binding.recyclerView.adapter= adapterLost
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getImages(){
        binding.progressBar3.visibility=View.VISIBLE
     firebaseFirestore.collection("images")
         .get().addOnSuccessListener {
             for (i in it){
                 mList.add(i.data["pic"].toString())
             }
             adapterLost.notifyDataSetChanged()
             binding.progressBar3.visibility=View.GONE

         }

    }

}