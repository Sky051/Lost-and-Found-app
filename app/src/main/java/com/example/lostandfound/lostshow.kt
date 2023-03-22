package com.example.lostandfound

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lostandfound.databinding.ActivityLostshowBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class lostshow : AppCompatActivity() {
    private lateinit var binding: ActivityLostshowBinding

    private lateinit var firebaseFirestore : FirebaseFirestore
    private var mList = mutableListOf<String>()
    private lateinit var adapter: imagesAdapter


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
        adapter= imagesAdapter(mList)
        binding.recyclerView.adapter= adapter
    }
    private fun getImages(){
     firebaseFirestore.collection("images")
         .get().addOnSuccessListener {
             for (i in it){
                 mList.add(i.data["pic"].toString())
             }
             adapter.notifyDataSetChanged()

         }

    }

}