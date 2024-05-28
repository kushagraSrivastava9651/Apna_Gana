package com.example.musicappui.Login_data

sealed class Screen(val route:String) {
    object LoginScreen : Screen("loginscreen")
    object SignupScreen : Screen("signupscreen")
    object ChatRoomsScreen : Screen("chatroomscreen")
    object ChatScreen : Screen("chatscreen")
    object MainView : Screen("main_screen")
}
