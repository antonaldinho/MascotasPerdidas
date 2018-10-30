package com.example.teamlgl.mascota2

import Database.MascotaData
import Database.MascotaDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar
    lateinit var instanceDatabase: MascotaDatabase

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_listaNormal -> {
                toolbar.title = "Mascotas perdidas"
                val listFragment = ListFragment.newInstance()
                openFragment(listFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_listaFavoritos -> {
                toolbar.title = "Mascotas favoritas"
                val listFavoritesFragment = ListFavoritesFragment.newInstance()
                openFragment(listFavoritesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_busqueda -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        button_add.setOnClickListener {
            Toast.makeText(this, "lol xdxd", Toast.LENGTH_SHORT).show()
        }

        //toolbar.title = "Mascotas perdidas"
        //val listFragment = ListFragment.newInstance()
        //val listFragment = ListFavoritesFragment.newInstance()
        //openFragment(listFragment)

        //instanceDatabase = MascotaDatabase.getInstance(this)
    }

}
