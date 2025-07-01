package com.itssagnikmukherjee.newsapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itssagnikmukherjee.newsapp.ui.screens.NewsScreen
import com.itssagnikmukherjee.newsapp.ui.screens.SplashScreen
import com.itssagnikmukherjee.newsapp.ui.viewmodels.NewsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(viewModel: NewsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = SplashScreen){
        composable<SplashScreen>{ SplashScreen(navController = navController) }
        composable<MainScreen>{ NewsScreen(viewModel = viewModel) }
    }
}