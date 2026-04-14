/*
* Programación Orientada a Objetos II - Grupo 1
* Formulario de Pedido en Android.
* Elaborado por Franya Gutierrez.
* Iniciado el 12 de Abril del 2026.
* Finalizado el 12 de Abril del 2026.
* */

package com.example.app_pedidos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_pedidos.ui.screens.FormularioScreen
import com.example.app_pedidos.ui.theme.App_PedidosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //quitar el innerpadding
            App_PedidosTheme {
                FormularioScreen()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App_PedidosTheme {
        FormularioScreen()
    }
}