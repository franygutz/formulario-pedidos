package com.example.app_pedidos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_pedidos.ui.theme.App_PedidosTheme
import kotlinx.coroutines.launch

//funcion principal donde se encuentran la pantalla del formulario, donde a su vez estan
// contempladas las validaciones de los atributos
@Composable
fun FormularioScreen(){
    // inicializacion de las variables del formulario
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var producto by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var notasAdicionales by remember { mutableStateOf("") }

    //validaciones de los atributos

    //1. validacion del nomrbe: al menos tres caracteres y que sea SoLAMENTE letras
    val nombreError = nombre.length < 3 || nombre.any {!it.isLetter()}
    //2. validacion del telefono: solo numeros y minimo 8 digitos
    val telefonoError = telefono.length < 8 || telefono.any {it.isLetter()}
    //3. direccion es obligatoria
    val direccionError = direccion.isBlank()
    // 4. la cantidad debe ser mayor que cero>
    //en este caso cree otra variable para poder asignar la cantidad, de esta forma la validacion es más eficiente
    val numero = cantidad.toIntOrNull()
    val cantidadError = numero == null || numero < 0

    //no se solicitó, pero por lógica supongo que no se pueden hacer pedidos sin productos, entonces lo hice un campo requerido
    val productoError = producto.isBlank()

    // snackBarHostState para el mensaje de error
    val snackbarHostState = remember { SnackbarHostState() }
    //recomposicion constanmte del formulario
    val scope = rememberCoroutineScope()
    //variable que permite habilitar el boto
    val formularioValido = !(nombreError || telefonoError || direccionError || cantidadError || productoError)


    //lo visual del formulario
    //el scaffold contiene el snackbarHostState para mostrar el mensaje de error
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState)}
    ) {
        // se centra la columna qyue contiene los datos que se solicitaran al usuario
        padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // encabezado del formulario
            Text("Pedido", style = MaterialTheme.typography.headlineMedium)

            //primer dato requerido: Nombre, donde se especifica lo relacionado al mismo
            Spacer(modifier = Modifier.padding(20.dp))
            OutlinedTextField(
                value = nombre,
                onValueChange = {nombre = it},
                label = {Text("Nombre")},
                isError = nombreError,
                //distintos mensajes para ayudar en la experiencia del usuario (por si esta haciendo algo mal) esto es aplicado tambien a
                // todos los outlinedtextfields implementados
                supportingText = {
                    if (nombreError) {
                        Text("El nombre no puede estar vacio ni contener numeros")
                    }
                    else{
                        Text("Dato obligatorio")
                    }
                }
            )

            // segundo dato requerido: telefono
            Spacer(modifier = Modifier.padding(20.dp))
            OutlinedTextField(
                value = telefono,
                onValueChange= {telefono = it},
                label = {Text("Telefono")},
                isError = telefonoError,
                supportingText = {
                    if (telefonoError){
                        Text("El telefono debe contener más de 8 digitos")
                    } else  {
                        Text("Dato obligatorio")
                    }
                }
            )

            //3. direccion
            Spacer(modifier = Modifier.padding(20.dp))
            OutlinedTextField(
                value = direccion,
                onValueChange = {direccion = it},
                label = {(Text("Dirección"))},
                isError = direccionError,
                supportingText = {
                    if (direccionError){
                        Text("La direccion no puede estar vacia")
                    } else Text("Dato obligatorio")
                }
            )

            //producto
            Spacer(modifier = Modifier.padding(20.dp))
            OutlinedTextField(
                value = producto,
                onValueChange = {producto = it},
                label = {Text("Producto")},
                isError = productoError,
                // como se dijo, yo considero que el producto debe ser un dato obligatorio, ya que sin el no hay pedido.
                supportingText = {
                    if (productoError){
                        Text("El producto no puede estar vacio")
                    } else Text("Dato obligatorio")
                }
            )

            //cantidad
            Spacer(modifier = Modifier.padding(20.dp))
            OutlinedTextField(
                value = cantidad,
                onValueChange = {cantidad = it},
                label = {Text("Cantidad")},
                isError = cantidadError,
                supportingText = {
                    if (cantidadError){
                        Text("El numero no puede ser negativo")
                    } else Text("Dato obligatorio")
                }
            )


            //notas adicionales
            Spacer(modifier = Modifier.padding(20.dp))
            OutlinedTextField(
                value = notasAdicionales,
                onValueChange = {notasAdicionales = it},
                label = {Text("Notas Adicionales")},
                supportingText = {Text("Dato opcional")}

            )


            //boton de enviar que se habilita si el formulario es valido
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    scope.launch {
                        //mensaje de envio de formulario (de ser exitoso
                        snackbarHostState.showSnackbar("¡Formulario enviado exitosamente!")
                    }
                    //limpieza de datos una vez enviados
                    nombre = ""
                    telefono = ""
                    direccion = ""
                    producto = ""
                    cantidad = ""
                    notasAdicionales = ""
                },
                enabled = formularioValido
            ) {
                Text("Enviar")
            }
        }
    }
}

//esta funcion no tiene relacion con lo solicitado en el ejercicio, es solamente para mi uso
//mientras desarrollo, para ir visualizando cada textbox que se va codificando
@Preview(showBackground = true)
@Composable
fun FormularioPreview() {
    App_PedidosTheme {
        FormularioScreen()
    }
}