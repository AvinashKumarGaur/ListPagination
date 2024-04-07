package com.example.demopagination.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.demopagination.model.Items
import com.google.accompanist.coil.rememberCoilPainter

class DetailsActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = intent.getParcelableExtra<Items>("repository")
        setContent {
            DetailsScreen(repository)

        }
    }
}
@Composable
fun DetailsScreen(repository: Items?) {
    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (repository != null) {
                Image(
                    painter = rememberCoilPainter(repository.owner?.avatarUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(400.dp),
                    contentScale = ContentScale.FillWidth
                )
                Text(text = repository.description ?: "No description available")
                val annotatedString = buildAnnotatedString {
                    append("Repository URL: ")
                    pushStringAnnotation(
                        tag = "URL",
                        annotation = repository.cloneUrl ?: ""
                    )
                    append(repository.cloneUrl ?: "")
                    pop()
                }
                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        val annotations = annotatedString.getStringAnnotations(
                            tag = "URL",
                            start = offset,
                            end = offset
                        )
                        if (annotations.isNotEmpty()) {
                            val url = annotations.first().item

                            val intent = Intent(context, WebViewActivity::class.java).apply {
                                putExtra(WebViewActivity.EXTRA_URL, url)
                            }
                            startActivity(context, intent, null)
                        }
                    },
                    style = TextStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)
                )
            } else {
                Text("No repository data received")
            }
        }
    }
}