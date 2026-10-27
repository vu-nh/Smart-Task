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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttasks.R

private val ConfirmBlue = Color(0xFF2196F3)
private val ConfirmDark = Color(0xFF30343B)
private val ConfirmGray = Color(0xFF858C9A)
private val ConfirmInputBackground = Color(0xFFF8F9FB)
private val ConfirmInputBorder = Color(0xFFD9DEE7)

@Composable
fun ConfirmScreen(
    email: String,
    otp: String,
    password: String,
    onBack: () -> Unit,
    onSubmit: () -> Unit
) {
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
                .background(ConfirmBlue)
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

        Spacer(modifier = Modifier.height(20.dp))

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
            color = ConfirmBlue
        )

        Spacer(modifier = Modifier.height(18.dp))

        // 4. Tiêu đề
        Text(
            text = "Confirm",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = ConfirmDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 5. Mô tả
        Text(
            text = "We are here to help you!",
            fontSize = 9.sp,
            color = ConfirmGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(18.dp))

        // 6. Thông tin Email
        ConfirmInfoField(
            icon = "♙",
            value = email
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 7. Thông tin OTP
        ConfirmInfoField(
            icon = "✉",
            value = otp
        )

        Spacer(modifier = Modifier.height(8.dp))

        // 8. Thông tin Password
        ConfirmInfoField(
            icon = "♙",
            value = "*".repeat(password.length)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 9. Nút Submit
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ConfirmBlue,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Submit",
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun ConfirmInfoField(
    icon: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(ConfirmInputBackground)
            .border(
                width = 1.dp,
                color = ConfirmInputBorder,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(horizontal = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = icon,
            fontSize = 11.sp,
            color = ConfirmGray
        )

        Spacer(modifier = Modifier.size(7.dp))

        Text(
            text = value,
            fontSize = 9.sp,
            color = ConfirmDark,
            maxLines = 1
        )
    }
}
