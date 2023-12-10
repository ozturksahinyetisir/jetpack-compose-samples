package com.ozturksahinyetisir.composeexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ozturksahinyetisir.composeexample.presentation.Home.HomeScreen
import com.ozturksahinyetisir.composeexample.presentation.Search.SearchScreen
import com.ozturksahinyetisir.composeexample.presentation.Slider.SliderScreen
import com.ozturksahinyetisir.composeexample.presentation.WebView.WebViewScreen

@Composable
fun NavigationContainer(navController:NavHostController,
){
    NavHost(navController = navController, startDestination = "home_screen" ){
        composable("home_screen"){
            HomeScreen(navController)
        }
        composable("search_screen"){
            SearchScreen()
        }
        composable("slider_screen"){
            SliderScreen()
        }
        composable("web_view_screen"){
            WebViewScreen()
        }
    }
}