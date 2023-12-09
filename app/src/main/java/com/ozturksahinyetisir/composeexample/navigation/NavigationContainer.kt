package com.ozturksahinyetisir.composeexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ozturksahinyetisir.composeexample.presentation.Home.HomeScreen
import com.ozturksahinyetisir.composeexample.presentation.Search.SearchScreen

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
    }
}