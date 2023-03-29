package com.example.lostandfound

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostandfound.databinding.ActivityFoundshowallBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference


class foundshowall : AppCompatActivity() {

    private lateinit var binding:ActivityFoundshowallBinding
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var nList = mutableListOf<String>()
    private var mList = mutableListOf<String>()
    private var oList = mutableListOf<String>()

    private lateinit var adapterfound: imagesAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoundshowallBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVars()
        getImages()
    }

    private fun initVars() {
        firebaseFirestore = FirebaseFirestore.getInstance()
        binding.recyclerView1.setHasFixedSize(true)
        binding.recyclerView1.layoutManager = LinearLayoutManager(this)
        adapterfound = imagesAdapter2(mList,nList,oList)
        binding.recyclerView1.adapter = adapterfound
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getImages(){
        binding.progressBar3.visibility = View.VISIBLE
        firebaseFirestore.collection("foundimages")
            .get().addOnSuccessListener {
                for(i in it){
                    mList.add(i.data["pic"].toString())
                    nList.add(i.data["name"].toString())
                    oList.add(i.data["details"].toString())


                }
                adapterfound.notifyDataSetChanged()
                binding.progressBar3.visibility = View.GONE
            }
    }

}
