package com.itssagnikmukherjee.newsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.itssagnikmukherjee.newsapp.data.const.Const.API_KEY
import com.itssagnikmukherjee.newsapp.data.local.ArticleEntity
import com.itssagnikmukherjee.newsapp.ui.viewmodels.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel, modifier: Modifier = Modifier) {
    val articles = viewModel.articles.observeAsState(emptyList())
    val apiKey = API_KEY

    LaunchedEffect(Unit) {
        viewModel.refresh(apiKey)
    }

    LazyColumn(modifier = modifier) {
        items(articles.value.size) { article ->
            NewsArticleItem(article = articles.value[article])
        }
    }
}

@Composable
fun NewsArticleItem(article: ArticleEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = article.title ?: "No Title",
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = article.description ?: "No Description"
        )

        article.urlToImage?.let { imageUrl ->
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = imageUrl,
                contentDescription = "Article Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }

        article.publishedAt?.let { publishDate ->
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Published: $publishDate",
            )
        }

        Divider(modifier = Modifier.padding(top = 16.dp))
    }
}