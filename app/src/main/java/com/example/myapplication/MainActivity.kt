package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val btn_Siguiente = findViewById<Button>(R.id.btnInicio)
        val ptUsuario = findViewById<EditText>(R.id.Usuario)
        val ptContra = findViewById<EditText>(R.id.contra)
        val ptAPodo = findViewById<EditText>(R.id.editTextText)

        btn_Siguiente.setOnClickListener{
            val usuario = ptUsuario.text.toString()
            val contrasenia = ptContra.text.toString()
            val apodo = ptAPodo.text.toString()

            if (usuario=="jorge") {
                if (contrasenia == "arribawindows") {
                    val intent = Intent(this, Actividad2::class.java)
                    intent.putExtra("usuario", usuario)

                    val sharedPref = this.getSharedPreferences("MiSharedPreferences", MODE_PRIVATE)
                    with(sharedPref.edit()){
                        putString("Apodo",apodo)
                        apply()
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Contrasenia erronea", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "usuario no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}