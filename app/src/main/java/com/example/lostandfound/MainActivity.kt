package com.example.lostandfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.lostandfound.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import java.util.NavigableMap

class MainActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityMainBinding
    private lateinit var drawerLayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(lost())




        binding.bottomnavigation.setOnItemSelectedListener {

            when (it.itemId){
                R.id.lost -> replaceFragment(lost())
                R.id.found -> replaceFragment(found())

                else ->{

                }
            }

            true
        }
    }

    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()



    }
}