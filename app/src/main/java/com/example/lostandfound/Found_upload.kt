package com.example.lostandfound

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.lostandfound.databinding.ActivityFoundUploadBinding

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Found_upload : AppCompatActivity() {
    private lateinit var binding : ActivityFoundUploadBinding
    private lateinit var storageRef : StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var imageUri1 : Uri? =null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFoundUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initvars()
        registerClickEvents()
    }

    private fun registerClickEvents(){
        binding.uploadbtn.setOnClickListener {
            uploadImage()

        }

        binding.showbtn.setOnClickListener {
            startActivity(Intent(this , foundshowall::class.java))
        }
        binding.imageView1.setOnClickListener{
            resultLauncher.launch("image/*")

        }
    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()){
        imageUri1 = it
        binding.imageView1.setImageURI(it)

    }



    private fun initvars(){
        storageRef = FirebaseStorage.getInstance().reference.child("Images")
        firebaseFirestore = FirebaseFirestore.getInstance()

    }

    private fun uploadImage(){
        binding.progressBar2.visibility=View.VISIBLE
        storageRef = storageRef.child(System.currentTimeMillis().toString())
        imageUri1?.let {
            storageRef.putFile(it).addOnCompleteListener{ task->
                if (task.isSuccessful){
                    storageRef.downloadUrl.addOnSuccessListener { uri->
                    val map = HashMap<String, Any>()
                    map["pic"] = uri.toString()

                    firebaseFirestore.collection("images").add(map).addOnCompleteListener {firestoretask->
                        if(firestoretask.isSuccessful){
                          Toast.makeText(this,"Uploaded Successfully", Toast.LENGTH_SHORT).show()
                        }else
                        {
                            Toast.makeText( this,firestoretask.exception?.message, Toast.LENGTH_SHORT).show()
                        }
                        binding.progressBar2.visibility=View.GONE
                        binding.imageView1.setImageResource(R.drawable.gallery)


                    }

                    }
                }else{
                    Toast.makeText(this,task.exception?.message ,Toast.LENGTH_SHORT).show()
                    binding.progressBar2.visibility = View.GONE
                    binding.imageView1.setImageResource(R.drawable.gallery)
                }

            }
        }
    }
}