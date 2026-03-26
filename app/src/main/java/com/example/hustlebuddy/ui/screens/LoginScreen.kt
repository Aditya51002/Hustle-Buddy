package com.example.hustlebuddy.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hustlebuddy.navigation.Screen
import com.example.hustlebuddy.ui.components.StudyBuddyButton
import com.example.hustlebuddy.ui.components.StudyBuddyTextField

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome Back!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Login to your account",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.outline
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        StudyBuddyTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email"
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        StudyBuddyTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            isPassword = true
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        StudyBuddyButton(
            text = "Login",
            onClick = {
                // TODO: Connect Firebase authentication here later
                // For now, just navigate to Home
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        TextButton(onClick = { navController.navigate(Screen.Signup.route) }) {
            Text("Don't have an account? Sign Up")
        }
    }
}
