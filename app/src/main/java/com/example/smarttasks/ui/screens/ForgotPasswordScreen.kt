package com.example.smarttasks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttasks.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width

private val ForgotBlue = Color(0xFF2196F3)
private val TextDark = Color(0xFF293548)
private val HintGray = Color(0xFF8A94A6)


@Composable
fun ForgotPasswordScreen(
    email: String,
    otp: String,
    password: String,
    isSubmitted: Boolean,
    onNext: (String) -> Unit
) {
    var inputEmail by remember {
        mutableStateOf(email)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
            .padding(top = 76.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 1. Logo UTH
        Image(
            painter = painterResource(id = R.drawable.logo_uth),
            contentDescription = "Logo UTH",
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 2. Tên ứng dụng
        Text(
            text = "SmartTasks",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = ForgotBlue
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 3. Tiêu đề
        Text(
            text = "Forgot Password?",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 4. Nội dung hướng dẫn
        Text(
            text = "Enter your Email, we will send you a verification\ncode.",
            fontSize = 10.sp,
            lineHeight = 14.sp,
            color = HintGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))


        // 5. Ô nhập email
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFFF8F9FB))
                .border(
                    width = 1.dp,
                    color = Color(0xFFD9DEE7),
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon email
            Text(
                text = "✉",
                fontSize = 14.sp,
                color = Color(0xFF9AA5B5)
            )

            Spacer(modifier = Modifier.width(6.dp))

            // Ô nhập email
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (inputEmail.isEmpty()) {
                    Text(
                        text = "Your Email",
                        fontSize = 14.sp,
                        color = Color(0xFF9AA5B5)
                    )
                }

                BasicTextField(
                    value = inputEmail,
                    onValueChange = { inputEmail = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 10.sp,
                        color = Color(0xFF293548)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )
            }
        }


        Spacer(modifier = Modifier.height(28.dp))

        // 6. Nút Next
        Button(
            onClick = {
                if (inputEmail.isNotBlank()) {
                    onNext(inputEmail.trim())
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ForgotBlue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Next",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // 7. Thông tin sau khi xác nhận
        if (isSubmitted) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Thông tin đã xác nhận",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF304878)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Email: $email",
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "OTP: $otp",
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Password: ${"*".repeat(password.length)}",
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
    }
}
