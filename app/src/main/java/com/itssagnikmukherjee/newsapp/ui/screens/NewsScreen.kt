package com.itssagnikmukherjee.newsapp.ui.screens

import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.itssagnikmukherjee.newsapp.data.const.Const.API_KEY
import com.itssagnikmukherjee.newsapp.data.local.ArticleEntity
import com.itssagnikmukherjee.newsapp.ui.theme.DarkGray
import com.itssagnikmukherjee.newsapp.ui.theme.Gray
import com.itssagnikmukherjee.newsapp.ui.theme.MyFont
import com.itssagnikmukherjee.newsapp.ui.theme.MyRed
import com.itssagnikmukherjee.newsapp.ui.theme.monsterat
import com.itssagnikmukherjee.newsapp.ui.viewmodels.NewsViewModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsScreen(viewModel: NewsViewModel, modifier: Modifier = Modifier) {
    val articles = viewModel.articles.observeAsState(emptyList())
    val apiKey = API_KEY

    LaunchedEffect(Unit) {
        viewModel.refresh(apiKey)
    }
    Column(
        modifier = Modifier.fillMaxSize().background(DarkGray).padding(top = 50.dp, start = 20.dp, end = 20.dp)
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row {
                Text(
                    "News",
                    fontSize = 36.sp,
                    fontFamily = MyFont,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(vertical = 20.dp),
                    color = MyRed
                )
                Text(
                    " Buddy",
                    fontSize = 32.sp,
                    fontFamily = MyFont,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(vertical = 20.dp),
                    color = Color.White
                )
                Text(
                    ".",
                    fontSize = 36.sp,
                    fontFamily = MyFont,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(vertical = 20.dp),
                    color = MyRed
                )
            }

            IconButton({}) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color.White)
            }
        }
        LazyColumn(modifier = modifier) {
            items(articles.value.size) { article ->
                    NewsArticleItem(article = articles.value[article])
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticleItem(article: ArticleEntity) {
    Box(
        modifier = Modifier.padding(vertical = 10.dp).clip(RoundedCornerShape(20.dp))
    ){
    Column(
        modifier = Modifier
            .fillMaxWidth().background(Color.White).padding(23.dp)
    ) {
        Text(
            text = article.title ?: "No Title",
            fontWeight = FontWeight.Bold,
            fontFamily = MyFont,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (article.description != null) {
            Text(
                text = article.description,
                modifier = Modifier.padding(vertical = 10.dp),
                fontFamily = monsterat,
                fontSize = 14.sp
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(Gray)
        ) {
            Column {
                article.urlToImage?.let { imageUrl ->
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = "Article Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp).clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop
                    )
                }

                article.publishedAt?.let { publishDate ->
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = formatDateTime(publishDate),
                        fontFamily = monsterat,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth().padding(end = 20.dp)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

            }
        }
    }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTime(isoString: String): String {
    val instant = Instant.parse(isoString)
    val zoned = instant.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm a", Locale.getDefault())
    return zoned.format(formatter)
}
