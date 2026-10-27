package com.example.smarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.smarttasks.ui.screens.ConfirmScreen
import com.example.smarttasks.ui.screens.ForgotPasswordScreen
import com.example.smarttasks.ui.screens.ResetPasswordScreen
import com.example.smarttasks.ui.screens.VerificationScreen
import com.example.smarttasks.ui.theme.SmartTasksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmartTasksTheme {
                var currentScreen by remember {
                    mutableStateOf("forgot")
                }

                var savedEmail by remember {
                    mutableStateOf("")
                }

                var savedOtp by remember {
                    mutableStateOf("")
                }

                var savedPassword by remember {
                    mutableStateOf("")
                }

                var isSubmitted by remember {
                    mutableStateOf(false)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (currentScreen) {
                        "forgot" -> {
                            ForgotPasswordScreen(
                                email = savedEmail,
                                otp = savedOtp,
                                password = savedPassword,
                                isSubmitted = isSubmitted,
                                onNext = { email ->
                                    savedEmail = email
                                    isSubmitted = false
                                    currentScreen = "verification"
                                }
                            )
                        }

                        "verification" -> {
                            VerificationScreen(
                                email = savedEmail,
                                onBack = {
                                    currentScreen = "forgot"
                                },
                                onNext = { otp ->
                                    savedOtp = otp
                                    currentScreen = "reset"
                                }
                            )
                        }

                        "reset" -> {
                            ResetPasswordScreen(
                                onBack = {
                                    currentScreen = "verification"
                                },
                                onNext = { password, _ ->
                                    savedPassword = password
                                    currentScreen = "confirm"
                                }
                            )
                        }

                        "confirm" -> {
                            ConfirmScreen(
                                email = savedEmail,
                                otp = savedOtp,
                                password = savedPassword,
                                onBack = {
                                    currentScreen = "reset"
                                },
                                onSubmit = {
                                    isSubmitted = true
                                    currentScreen = "forgot"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
