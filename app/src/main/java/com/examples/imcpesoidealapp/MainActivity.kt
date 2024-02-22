package com.examples.imcpesoidealapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.examples.imcpesoidealapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val f1 = 2.25;
    private val f2 = 45;
    private val m1 = 2.7
    private val m2 = 47.75
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun imc(view: View) {
        if (binding.pesovalor.text.toString().trim().isEmpty()) {
            binding.pesovalor.requestFocus()
            Toast.makeText(this, "Favor de ingresar el peso", Toast.LENGTH_LONG).show()
        } else if (binding.estaturavalor.text.toString().trim().isEmpty()) {
            binding.estaturavalor.requestFocus()
            Toast.makeText(this, "Favor de ingresar la estatura", Toast.LENGTH_LONG).show()
        } else {
            val kg: Float = java.lang.Float.parseFloat(binding.pesovalor.text.toString())
            val m: Float = java.lang.Float.parseFloat(binding.estaturavalor.text.toString())
            val bmi = (kg / (m * m))
            if (bmi < 18.0) {
                Toast.makeText(this, "Tu imc es " + bmi + "\nBajo peso", Toast.LENGTH_LONG).show()
            } else {
                if (bmi < 24.9) {
                    Toast.makeText(this, "Tu imc es " + bmi + "\nPeso normal", Toast.LENGTH_LONG)
                        .show()
                } else {
                    if (bmi < 29.9) {
                        Toast.makeText(this, "Tu imc es " + bmi + "\nSobre peso", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        if (bmi < 34.9) {
                            Toast.makeText(
                                this,
                                "Tu imc es " + bmi + "\nObesidad",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            if (bmi > 35) {
                                Toast.makeText(
                                    this,
                                    "Tu imc es " + bmi + "\nMuy obeso",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
                binding.resultadovalor.setText(bmi.toString())
            }
        }
    }

    fun borrar(view: View) {
        binding.pesovalor.setText("")
        binding.estaturavalor.setText("")
        binding.resultadovalor.setText("")
        binding.fem.isEnabled = true
        binding.mas.isEnabled = true
        binding.mas.isChecked = false
        binding.fem.isChecked = false
    }

    fun pesoideal(view: View) {
        if (binding.pesovalor.text.toString().trim().isEmpty()) {
            binding.pesovalor.requestFocus()
            Toast.makeText(this, "Favor de ingresar el peso", Toast.LENGTH_LONG).show()
            return
        } else if (binding.estaturavalor.text.toString().trim().isEmpty()) {
            binding.estaturavalor.requestFocus()
            Toast.makeText(this, "Favor de ingresar la estatura", Toast.LENGTH_LONG).show()
            return
        } else if (binding.mas.isChecked == false && binding.fem.isChecked == false) {
            Toast.makeText(this, "Favor seleccionar un genero", Toast.LENGTH_LONG).show()
            return
        }
        var pi = 0.00;
        val kg = java.lang.Double.parseDouble(binding.pesovalor.text.toString())
        val m = java.lang.Double.parseDouble(binding.estaturavalor.text.toString())

        if (binding.fem.isChecked) {
            binding.mas.isEnabled = false
            pi = ((((100 * m) - 152.4) / 2.54) * f1) + f2;
        } else if (binding.mas.isChecked) {
            binding.fem.isEnabled = false
            pi = ((((100 * m) - 152.4) / 2.54) * m1) + m2;
        }

        val piUp = (pi * 0.10) + pi
        Toast.makeText(this, "Resultado del peso ideal $pi", Toast.LENGTH_LONG).show()
        binding.resultadovalor.setText(pi.toString())
        val piDown = (pi - (pi * 0.10))

        if (kg > pi) {
            Toast.makeText(this, "Estás arriba de tu peso ideal", Toast.LENGTH_LONG).show()
        } else if (kg <= piUp && kg >= piDown) {
            Toast.makeText(this, "Estás en de tu peso ideal", Toast.LENGTH_LONG).show()
        } else if (kg < piDown){
            Toast.makeText(this, "Estás abajo de tu peso ideal", Toast.LENGTH_LONG).show()

        }

    }
}