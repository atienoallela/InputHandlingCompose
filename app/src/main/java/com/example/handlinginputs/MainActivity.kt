package com.example.handlinginputs

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.handlinginputs.ui.theme.HandlingInputsTheme
import com.example.handlinginputs.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandlingInputsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize() ,
                    color = MaterialTheme.colorScheme.background
                ) {
                    HandlingInputs()
                }
            }
        }
    }
}

@Composable
fun HandlingInputs( modifier : Modifier = Modifier) {
    var username by remember { mutableStateOf("")}
    var emailme by rememberSaveable {
        mutableStateOf("")
    }
    val brush = remember {
        val rainbowColors = listOf(Purple40, Color.Cyan, Color.Gray, Color.Blue)
        Brush.linearGradient(
            colors = rainbowColors
        )
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var isValid by remember {
        mutableStateOf(false)
    }
val context = LocalContext.current
    Column(modifier = modifier.padding(30.dp)) {
        OutlinedTextField(
            value = username ,
            onValueChange = {input ->
                username = input
                isValid = input.isNotEmpty()
                            } ,
            textStyle = TextStyle(brush = brush) ,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp) ,
            label = { Text(text = "Username") },
            isError = !isValid
        )
       
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = emailme ,
            onValueChange = { emailme = it } ,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp) ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email) ,
            label = { Text(text = "Enter Email") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password ,
            onValueChange = { password = it } ,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp) ,
            visualTransformation = PasswordVisualTransformation() ,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password) ,
            label = { Text(text = "Enter Password") }
        )
        Button(onClick = {
            Toast.makeText(context,"Atieno Submitted",Toast.LENGTH_SHORT).show()
        }) {
            if (!isValid){
                Text(text = "Please enter username")
            }else{
                Text(text = "Submit")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HandlerPreview() {
    HandlingInputsTheme {
        HandlingInputs()
    }
}