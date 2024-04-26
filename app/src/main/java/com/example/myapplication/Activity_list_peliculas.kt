package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Activity_list_peliculas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_peliculas)

        var lv_peliculas = findViewById<ListView>(R.id.lv_peliculas)
        var list_peliculas: ArrayList<String> =ArrayList()
        var database =FirebaseDatabase.getInstance()
        var peliculasRef = database.getReference("peliculas")

        var adaptador = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list_peliculas)
        lv_peliculas.adapter= adaptador

        peliculasRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                adaptador.clear()
                for (registro in snapshot.children){
                    val pelicula =registro.getValue(Pelicula::class.java)
                    val textoPelicula="titulo: "+ pelicula?.titulo+", genero: "+pelicula?.genero
                    list_peliculas.add(textoPelicula)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("error al consultar las peliculas:${error.message}")
            }
        })

    }
}