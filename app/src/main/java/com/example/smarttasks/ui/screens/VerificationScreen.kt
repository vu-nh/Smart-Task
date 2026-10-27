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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttasks.R

private val VerificationBlue = Color(0xFF2196F3)
private val VerificationDark = Color(0xFF293548)
private val VerificationGray = Color(0xFF858C9A)
private val OtpBackground = Color(0xFFF3F4F6)
private val OtpBorder = Color(0xFFE5E7EB)

@Composable
fun VerificationScreen(
    email: String,
    onBack: () -> Unit,
    onNext: (String) -> Unit
) {
    val otpDigits = remember {
        mutableStateListOf("", "", "", "", "")
    }

    val otp = otpDigits.joinToString("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // 1. Nút quay lại
        Box(
            modifier = Modifier
                .align(Alignment.Start)
                .size(40.dp)
                .clickable { onBack() },
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .background(VerificationBlue),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "‹",
                    fontSize = 30.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

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
            color = VerificationBlue
        )

        Spacer(modifier = Modifier.height(36.dp))

        // 4. Tiêu đề
        Text(
            text = "Verify Code",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = VerificationDark
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 5. Mô tả
        Text(
            text = "Enter the code\nwe just sent you on your registered Email",
            fontSize = 9.sp,
            lineHeight = 13.sp,
            color = VerificationGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(30.dp))

        // 6. Năm ô nhập OTP
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(5) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(OtpBackground)
                        .border(
                            width = 1.dp,
                            color = OtpBorder,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    BasicTextField(
                        value = otpDigits[index],
                        onValueChange = { value ->
                            otpDigits[index] = value
                                .filter { it.isDigit() }
                                .takeLast(1)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = VerificationDark,
                            textAlign = TextAlign.Center
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                innerTextField()
                            }
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 7. Nút Next
        Button(
            onClick = {
                if (otp.length == 5) {
                    onNext(otp)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = VerificationBlue,
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
