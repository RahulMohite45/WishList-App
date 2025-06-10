package com.example.mywishlistapp

sealed class Screen(val route:String) {
    object HomeScreen: Screen("home_screen")
    object Addscreen : Screen("add_screen")
}