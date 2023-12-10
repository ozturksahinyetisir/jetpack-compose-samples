package com.ozturksahinyetisir.composeexample.presentation.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(
            onClick = { navController.navigate("search_screen") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
        ) {
            Text("Search Screen")
        }
        OutlinedButton(
            onClick = { navController.navigate("slider_screen") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
        ) {
            Text("Slider Screen")
        }
        OutlinedButton(
            onClick = { navController.navigate("web_view_screen") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)
        ) {
            Text("WebView Screen")
        }
    }

}