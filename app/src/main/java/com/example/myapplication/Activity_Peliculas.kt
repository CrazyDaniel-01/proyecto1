package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class Activity_Peliculas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_peliculas)
        val pt_titulo = findViewById<EditText>(R.id.txttitulo)
        val sp_genero = findViewById<Spinner>(R.id.spinner)
        val btnGuardar = findViewById<Button>(R.id.btnguardar)

        btnGuardar.setOnClickListener{
            //primero tomamos los datos que se agregaron como título
            val titulo = pt_titulo.text.toString()
            val genero = sp_genero.selectedItem.toString()
            val database = FirebaseDatabase.getInstance()

            val peliculasRef = database.getReference("peliculas")
            val peliculaId=peliculasRef.push().key
            val pelicula = Pelicula(titulo,genero)

            peliculaId?.let{
                peliculasRef.child(it).setValue(pelicula)
                    .addOnSuccessListener{
                        Toast.makeText(this, "Se agregó la pelicula", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Activity_list_peliculas::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener{
                        Toast.makeText(this,"Error al guardar la pelicula", Toast.LENGTH_SHORT).show()
                        println("Error al guardar la pelicula en Firebase: ${it.message}")
                    }
            }

        }

    }
}