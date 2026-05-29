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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.InputField
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity

    fun handleLogin() {
        if(email.isBlank() || password.isBlank()) {
            Toast.makeText(activity, "Insira um Email e uma Senha", Toast.LENGTH_SHORT).show()
            return;
        }

        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if(task.isSuccessful) {
                    activity.startActivity(
                        Intent(activity, MainActivity::class.java).setFlags(
                            FLAG_ACTIVITY_SINGLE_TOP
                        )
                    )
                    Toast.makeText(activity, "Login OK!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity, "Login falhou!", Toast.LENGTH_LONG).show()
                }
            }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Bem-vindo/a!", fontSize = 24.sp)

        Spacer(modifier = Modifier.size(12.dp))

        InputField(
             value = email,
             onValueChange = { email = it },
             label = "Digite seu e-mail",
             modifier = Modifier.fillMaxWidth(0.9f),
        )

        Spacer(modifier = Modifier.size(12.dp))

        InputField(
            value = password,
            onValueChange = { password = it },
            label = "Digite sua senha",
            modifier = Modifier.fillMaxWidth(0.9f),
            isPassword = true
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { handleLogin() },
                enabled = email.isNotEmpty() && password.isNotEmpty()) {
                Text("Login")
            }
            Button(
                onClick = { activity.startActivity(
                    Intent(activity, RegisterActivity::class.java).setFlags(
                        FLAG_ACTIVITY_SINGLE_TOP
                    )
                )},
            ) {
                Text("Criar Conta")
            }
            Button(onClick = { email = ""; password = "" }) {
                Text("Limpar")
            }
        }
    }
}