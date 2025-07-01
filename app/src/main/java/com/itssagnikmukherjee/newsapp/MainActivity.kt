package com.itssagnikmukherjee.newsapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.itssagnikmukherjee.newsapp.data.const.Const.BASE_URL
import com.itssagnikmukherjee.newsapp.data.local.AppDatabase
import com.itssagnikmukherjee.newsapp.data.remote.NewsApiService
import com.itssagnikmukherjee.newsapp.data.repo.NewsRepository
import com.itssagnikmukherjee.newsapp.ui.screens.NewsScreen
import com.itssagnikmukherjee.newsapp.ui.theme.NewsappTheme
import com.itssagnikmukherjee.newsapp.ui.viewmodels.NewsViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    val db = Room.databaseBuilder(context, AppDatabase::class.java, "news-db").build()
                    val api = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(NewsApiService::class.java)
                    val repo = NewsRepository(api, db.articleDao())
                    val viewmodel = NewsViewModel(repo)
                         NewsScreen(viewModel = viewmodel)
            }
        }
    }
}
