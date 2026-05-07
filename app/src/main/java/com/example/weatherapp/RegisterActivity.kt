package com.example.weatherapp

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.theme.WeatherAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.weatherapp.ui.theme.InputField

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity;

    fun clearFields() {
        name = "";
        password= "";
        confirmPassword= "";
        email= "";
        return
    }

    fun register() {
        if(password != confirmPassword) {
            return Toast.makeText(activity, "as senhas estão diferentes!", Toast.LENGTH_LONG).show()
        }
        Toast.makeText(activity, "Registrado com Sucesso!", Toast.LENGTH_LONG).show()
        activity.startActivity(
            Intent(activity, LoginActivity::class.java).setFlags(
                FLAG_ACTIVITY_SINGLE_TOP
            )
        )
    }

    val isRegisterEnabled: Boolean = name.isNotEmpty() &&
                                     email.isNotEmpty() &&
                                     password.isNotEmpty() &&
                                     confirmPassword.isNotEmpty()


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        InputField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = name,
            onValueChange = { name = it },
            label = "Insira o Nome"
        )
        Spacer(
            Modifier.size(24.dp)
        )
        InputField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = email,
            onValueChange = { email = it },
            label = "Insira o Email"
        )
        Spacer(
            Modifier.size(24.dp)
        )
        InputField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = password,
            onValueChange = { password = it },
            label = "Insira a Senha",
            isPassword = true
        )
        Spacer(
            Modifier.size(24.dp)
        )
        InputField(
            modifier = Modifier.fillMaxWidth(0.9f),
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirme sua Senha",
            isPassword = true
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                { register() },
                enabled = isRegisterEnabled
            ) {
                Text(
                    "Registrar"
                )
            }
            Button(
                { clearFields() }
            ) {
                Text(
                    "Limpar"
                )
            }
        }
    }
}