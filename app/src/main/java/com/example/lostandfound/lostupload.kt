package com.example.lostandfound

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lostandfound.databinding.ActivityLostuploadBinding
import com.example.lostandfound.databinding.FragmentFoundBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class lostupload : AppCompatActivity() {

    private lateinit var binding: ActivityLostuploadBinding
    private lateinit var storageRef :StorageReference
    private lateinit var firebaseFirestore : FirebaseFirestore
    private var imageUri : Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivityLostuploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVars()
        registerClickEvents()
    }
    private fun registerClickEvents(){
        binding.uploadbtn.setOnClickListener {
            uploadImage()
        }
        binding.showbtn.setOnClickListener {
            startActivity(Intent(this, lostshow::class.java))
        }
        binding.imageView.setOnClickListener{
       resultLauncher.launch("image/*")
       }

    }
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()){

        imageUri = it
        binding.imageView.setImageURI(it)

    }

    private fun initVars(){
        storageRef = FirebaseStorage.getInstance().reference.child("Images")
        firebaseFirestore = FirebaseFirestore.getInstance()
    }
    private fun uploadImage(){
        binding.progressBar2.visibility = View.VISIBLE
        storageRef = storageRef.child(System.currentTimeMillis().toString())
        imageUri?.let{
            storageRef.putFile(it).addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    storageRef.downloadUrl.addOnSuccessListener { uri ->

                        val map = HashMap<String , Any>()
                        map["pic"]= uri.toString()

                        firebaseFirestore.collection("images").add(map).addOnCompleteListener {FirestoreTask ->

                        if (FirestoreTask.isSuccessful){
                            Toast.makeText( this,"Uploaded Successfully",Toast.LENGTH_SHORT).show()

                        }else{
                            Toast.makeText(this,task.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                            binding.progressBar2.visibility = View.GONE
                            binding.imageView.setImageResource(R.drawable.gallery)


                        }
                    }

                }else{
                    Toast.makeText(this , task.exception?.message , Toast.LENGTH_SHORT).show()
                    binding.progressBar2.visibility = View.GONE
                    binding.imageView.setImageResource(R.drawable.gallery)
                }
            }
        }
    }
}