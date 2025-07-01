package com.itssagnikmukherjee.newsapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.itssagnikmukherjee.newsapp.ui.navigation.MainScreen
import com.itssagnikmukherjee.newsapp.ui.theme.DarkGray
import com.itssagnikmukherjee.newsapp.ui.theme.MyFont
import com.itssagnikmukherjee.newsapp.ui.theme.MyRed
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LaunchedEffect(Unit) {
            delay(1000)
            navController.navigate(MainScreen)
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Row {
                Text(
                    "News",
                    fontSize = 48.sp,
                    fontFamily = MyFont,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(vertical = 20.dp),
                    color = MyRed
                )
                Text(
                    " Buddy",
                    fontSize = 48.sp,
                    fontFamily = MyFont,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(vertical = 20.dp),
                    color = Color.White
                )
                Text(
                    ".",
                    fontSize = 48.sp,
                    fontFamily = MyFont,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(vertical = 20.dp),
                    color = MyRed
                )
            }
        }
    }
}