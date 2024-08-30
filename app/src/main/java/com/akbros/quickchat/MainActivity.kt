package com.akbros.quickchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.akbros.quickchat.screens.ChatScreen
import com.akbros.quickchat.screens.LoginScreen
import com.akbros.quickchat.screens.SignUpScreen
import com.akbros.quickchat.screens.UserListScreen
import com.akbros.quickchat.ui.theme.QuickChatTheme
import com.parse.ParseUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatApp()
        }

    }



    @Composable
    fun ChatApp() {
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                if (ParseUser.getCurrentUser() != null) {
                    navController.navigate("users") {
                        popUpTo("splash") { inclusive = true }
                    }
                } else {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            }
        }

        NavHost(navController = navController, startDestination = "splash") {
            composable("splash"){

            }
            composable("login") {
                LoginScreen(navController)
            }
            composable("signup") {
                SignUpScreen(navController)
            }
            composable("users"){
                UserListScreen(navController)
            }
            composable("chat/{userId}") { backStackEntry ->
                val userId = backStackEntry.arguments?.getString("userId")
                userId?.let {
                    ChatScreen(
                        userId = userId
                    )
                }
            }

        }
    }
}
