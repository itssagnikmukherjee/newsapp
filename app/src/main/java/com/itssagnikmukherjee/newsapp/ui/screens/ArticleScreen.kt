package com.itssagnikmukherjee.newsapp.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.itssagnikmukherjee.newsapp.data.remote.Article
import com.itssagnikmukherjee.newsapp.ui.theme.DarkGray
import com.itssagnikmukherjee.newsapp.ui.theme.MyFont
import com.itssagnikmukherjee.newsapp.ui.theme.MyRed
import com.itssagnikmukherjee.newsapp.ui.theme.monsterat

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleScreen(navController: NavController, title: String, description: String, imageUrl: String?, publishedAt: String?, url: String?) {
    val context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize().background(DarkGray)
    ){
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val article = Article(
                title = title,
                description = description,
                urlToImage = imageUrl,
                publishedAt = publishedAt.toString(),
                url = url
            )


            Column {
                if (article.urlToImage != "") {
                    Box(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp))) {
                        AsyncImage(model = article.urlToImage, contentDescription = "Article Image")
                    }
                }
                Spacer(Modifier.height(20.dp))
                Text(
                    text = article.title ?: "No Title",
                    fontSize = 28.sp,
                    fontFamily = MyFont,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(Modifier.height(20.dp))

                Text(
                    text = article.description ?: "No Description",
                    fontSize = 16.sp,
                    fontFamily = monsterat,
                    color = Color.White,
                    modifier = Modifier.alpha(0.6f)
                )
            }

            Spacer(Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                Text(
                    text = formatDateTime(article.publishedAt.toString()),
                    fontSize = 14.sp,
                    fontFamily = monsterat,
                    color = Color.White
                )
            }

            Button(
                onClick = {
                    article.url?.let { url ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MyRed,
                    contentColor = Color.White
                )
            ) {
                Text("Read More")
            }
        }
    }
}