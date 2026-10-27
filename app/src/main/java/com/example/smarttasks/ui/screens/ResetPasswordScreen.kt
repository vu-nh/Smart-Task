package com.example.smarttasks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttasks.R

private val ResetBlue = Color(0xFF2196F3)
private val ResetDark = Color(0xFF293548)
private val ResetGray = Color(0xFF858C9A)
private val ResetInputBackground = Color(0xFFF8F9FB)
private val ResetInputBorder = Color(0xFFD9DEE7)

@Composable
fun ResetPasswordScreen(
    onBack: () -> Unit,
    onNext: (String, String) -> Unit
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 1. Nút quay lại
        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .size(30.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(ResetBlue)
                .clickable { onBack() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "‹",
                fontSize = 28.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 2. Logo UTH
        Image(
            painter = painterResource(id = R.drawable.logo_uth),
            contentDescription = "Logo UTH",
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 3. Tên ứng dụng
        Text(
            text = "SmartTasks",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = ResetBlue
        )

        Spacer(modifier = Modifier.height(18.dp))

        // 4. Tiêu đề
        Text(
            text = "Create new password",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = ResetDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 5. Mô tả
        Text(
            text = "Your new password must be different from\npreviously used password",
            fontSize = 9.sp,
            lineHeight = 13.sp,
            color = ResetGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 6. Ô nhập mật khẩu
        ResetPasswordField(
            value = password,
            onValueChange = {
                password = it
                errorMessage = ""
            },
            placeholder = "Password"
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 7. Ô xác nhận mật khẩu
        ResetPasswordField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                errorMessage = ""
            },
            placeholder = "Confirm Password"
        )

        // 8. Thông báo lỗi
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = errorMessage,
                fontSize = 11.sp,
                color = Color.Red,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 9. Nút Next
        Button(
            onClick = {
                when {
                    password.isBlank() || confirmPassword.isBlank() -> {
                        errorMessage = "Vui lòng nhập đầy đủ mật khẩu"
                    }

                    password != confirmPassword -> {
                        errorMessage = "Mật khẩu xác nhận không khớp"
                    }

                    else -> {
                        onNext(password, confirmPassword)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ResetBlue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Next",
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun ResetPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(ResetInputBackground)
            .border(
                width = 1.dp,
                color = ResetInputBorder,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Biểu tượng khóa đơn giản
        Text(
            text = "♙",
            fontSize = 11.sp,
            color = ResetGray
        )

        Spacer(modifier = Modifier.size(6.dp))

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            if (value.isEmpty()) {
                Text(
                    text = placeholder,
                    fontSize = 9.sp,
                    color = ResetGray
                )
            }

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 10.sp,
                    color = ResetDark
                ),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
        }
    }
}
