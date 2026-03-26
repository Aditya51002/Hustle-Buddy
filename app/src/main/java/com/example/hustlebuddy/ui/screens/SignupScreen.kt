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
fun SignupScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create Account",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Start your study journey today",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.outline
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        StudyBuddyTextField(
            value = name,
            onValueChange = { name = it },
            label = "Full Name"
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
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

        Spacer(modifier = Modifier.height(16.dp))
        
        StudyBuddyTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            isPassword = true
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        StudyBuddyButton(
            text = "Sign Up",
            onClick = {
                // TODO: Connect Firebase authentication here later
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Signup.route) { inclusive = true }
                }
            }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        TextButton(onClick = { navController.navigateUp() }) {
            Text("Back to Login")
        }
    }
}
