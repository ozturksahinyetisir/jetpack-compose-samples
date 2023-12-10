package com.ozturksahinyetisir.composeexample.presentation.WebView

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun WebViewScreen() {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl("https://github.com/ozturksahinyetisir/jetpack-compose-samples/blob/master/README.md")
        }
    }
    Column {
       AndroidView(factory = { webView })
    }

}