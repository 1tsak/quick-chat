package com.akbros.quickchat.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.akbros.quickchat.ui.theme.QuickChatTheme
import com.parse.ParseUser


@Composable
fun SignUpScreen(
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    QuickChatTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFAFAFA)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                val context = LocalContext.current
                Text(
                    text = "Create an Account",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(32.dp))

                BasicTextField(
                    value = email,
                    onValueChange = { email = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFEEEEEE), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp),
                    textStyle = LocalTextStyle.current.copy(
                        color = Color(0xFF333333),
                        fontSize = 16.sp
                    ),
                    decorationBox = { innerTextField ->
                        if (email.isEmpty()) {
                            Text(text = "Email", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFEEEEEE), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp),
                    textStyle = LocalTextStyle.current.copy(
                        color = Color(0xFF333333),
                        fontSize = 16.sp
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    decorationBox = { innerTextField ->
                        if (password.isEmpty()) {
                            Text(text = "Password", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                BasicTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFEEEEEE), shape = MaterialTheme.shapes.medium)
                        .padding(16.dp),
                    textStyle = LocalTextStyle.current.copy(
                        color = Color(0xFF333333),
                        fontSize = 16.sp
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    decorationBox = { innerTextField ->
                        if (confirmPassword.isEmpty()) {
                            Text(text = "Confirm Password", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { signup(email,password, context,navController)  },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text(text = "Sign Up", color = Color.White, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick ={
                    navController.navigate("login")
                }) {
                    Text(text = "Already have an account? Log in", color = Color(0xFF6200EE))
                }
            }
        }
    }
}

fun signup(email: String, password: String,context: Context,navController: NavController) {

    val user = ParseUser();
    user.setUsername(email);
    user.setPassword(password);
    user.signUpInBackground {
        if (it == null) {
            navController.navigate("login")
            Toast.makeText(context, "SignUp Successful", Toast.LENGTH_LONG).show();
        } else {
            ParseUser.logOut();
            Toast.makeText(context, it.message, Toast.LENGTH_LONG).show();
        }
    }


}