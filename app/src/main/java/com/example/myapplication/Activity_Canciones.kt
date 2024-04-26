package com.example.myapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import layout.CancionesHelper

class Activity_Canciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_canciones)

        val pt_titulo = findViewById<TextView>(R.id.tvcanciones)
        val pt_cantante = findViewById<TextView>(R.id.textView5)
        val btnGuardar = findViewById<Button>(R.id.button)

        btnGuardar.setOnClickListener{
            val canciones=CancionesHelper(this,"cancionesbd",null,1)
            val bd =canciones.writableDatabase
            val registro = ContentValues()
            registro.put("titulo",pt_titulo.text.toString())
            registro.put("cantante",pt_cantante.text.toString())
            bd.insert("cancion",null,registro)
            bd.close()
            pt_titulo.setText("'")
            pt_cantante.setText("")
            Toast.makeText(this, "se agrego la cancion sin acento", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, activity_list_canciones::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}