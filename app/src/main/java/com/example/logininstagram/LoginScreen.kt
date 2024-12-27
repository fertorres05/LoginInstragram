package com.example.logininstagram

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun LoginScreen(){
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ){
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center))
        Footer(Modifier.align(Alignment.BottomCenter))
    }
}



@Composable
fun Header(modifier: Modifier){
    val activity= LocalContext.current as? Activity
    Icon(imageVector=Icons.Default.Close,
        contentDescription = "Close App",
        modifier= modifier.clickable{activity?.finish()})
}

@Composable
fun Body(modifier: Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("")}
    var isLoginEnable by rememberSaveable { mutableStateOf(false)}
    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier=Modifier.size(16.dp))
        Email(email) {email=it}
        Spacer(modifier=Modifier.size(8.dp))
        Password(password) {password=it}
        Spacer(modifier=Modifier.size(8.dp))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier=Modifier.size(16.dp))
        LoginButton(isLoginEnable)
        Spacer(modifier=Modifier.size(32.dp))
        LoginDivisor()
        Spacer(modifier=Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(
        modifier=modifier.fillMaxWidth()
    ){
        Divider(
            Modifier
                .background(Color(0xFF9F9F9F))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier=Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            Text(
                text = "Don't have an account",
                fontSize=12.sp,
                color=Color(0xFFB5B5B5),
                modifier=Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "Sign up",
                fontSize = 12.sp,
                color=Color(0xFF4EA8E9),
                fontWeight = FontWeight.Bold,
                //modifier=Modifier.clickable { createAccount }
            )
        }
    }
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id=R.drawable.insta),
        contentDescription = "Logo",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, function: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = {function(it)},
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        placeholder={ Text(text="Email") },
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2), // Color del texto cuando está enfocado
            unfocusedTextColor = Color(0xFFB2B2B2), // Color del texto cuando no está enfocado
            containerColor = Color(0xFFFAFAFA), // Color de fondo del TextField
            focusedIndicatorColor = Color.Transparent, // Color del indicador cuando está enfocado
            unfocusedIndicatorColor = Color.Transparent // Color del indicador cuando no está enfocado
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email //Especifica el teclado de tipo Email
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, function: (String) -> Unit) {
    var passVisibility by rememberSaveable { mutableStateOf(false)}
    TextField(
        value = password,
        onValueChange = {function(it)},
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        placeholder={ Text(text="Password") },
        colors = TextFieldDefaults.textFieldColors(
            focusedTextColor = Color(0xFFB2B2B2), // Color del texto cuando está enfocado
            unfocusedTextColor = Color(0xFFB2B2B2), // Color del texto cuando no está enfocado
            containerColor = Color(0xFFFAFAFA), // Color de fondo del TextField
            focusedIndicatorColor = Color.Transparent, // Color del indicador cuando está enfocado
            unfocusedIndicatorColor = Color.Transparent // Color del indicador cuando no está enfocado
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password // Especifica el teclado de tipo Password
        ),
        trailingIcon = {
            val image=if(passVisibility)
                Icons.Filled.VisibilityOff
            else
                Icons.Filled.Visibility
            IconButton(onClick={passVisibility=!passVisibility}){
                Icon(imageVector = image, contentDescription = "Show password")
            }
        },
        //Cambia como se va el texti
        visualTransformation = if(passVisibility)
            VisualTransformation.None
        else
            PasswordVisualTransformation()
    )
}
@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot password",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4EA8E9),
        modifier = modifier
            //.clickable{sendNewPassword}
    )
}

@Composable
fun LoginButton(isLoginEnable: Boolean) {
    Button(
        onClick = {checkLogin()},
        enabled = isLoginEnable,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor=Color.White,
            containerColor=Color(0xFF4EA8E9),
            disabledContentColor = Color.White,
            disabledContainerColor = Color(0XFF78C8F9)
        )
    ){
        Text(text = "Log In")
    }
}

fun checkLogin() {
    TODO("Not yet implemented")
}

@Composable
fun LoginDivisor() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Divider(
            Modifier
                .background(Color(0xFF9F9F9F))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 6.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color=Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFF9F9F9F))
                .height(1.dp)
                .weight(1f)
        )
    }
}

@Composable
fun SocialLogin() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id=R.drawable.fb),
            contentDescription = "FB",
            modifier = Modifier.size(16.dp)
        )

        Text(
            text = "Continue as Fernando",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4EA8E9),
            modifier = Modifier.
                padding(horizontal = 8.dp)
               // .clickable { checkSocialLogin() }

        )
    }
}

