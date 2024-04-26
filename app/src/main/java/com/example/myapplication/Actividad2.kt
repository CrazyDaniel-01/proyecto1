package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Actividad2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad2)

        val tv_Bienvenida = findViewById<TextView>(R.id.textView2)
        val nombreUsuario = intent.getStringExtra("usuario")
        val sharedPref = this.getSharedPreferences("MiSharedPreferences", MODE_PRIVATE)
        val apodo = sharedPref.getString("Apodo", "")
        tv_Bienvenida.append(" " + apodo)

        val botonPelicula = findViewById<Button>(R.id.btnpel)
        val botonCanciones = findViewById<Button>(R.id.btncal)

        botonPelicula.setOnClickListener {
            val intent = Intent(this,Activity_Peliculas::class.java)
            startActivity(intent)
        }

        botonCanciones.setOnClickListener {
            val intent = Intent(this, Activity_Canciones::class.java)
            startActivity(intent)
        }
    }
}