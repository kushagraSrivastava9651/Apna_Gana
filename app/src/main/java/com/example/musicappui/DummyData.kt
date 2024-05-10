package com.example.musicappui

import androidx.annotation.DrawableRes

data class Lib(@DrawableRes val icon:Int, val name:String)

val libraries= listOf<Lib>(
    Lib(R.drawable.baseline_library_music_24,"PlayList"),
    Lib(R.drawable.baseline_sports_martial_arts_24,"Artist"),
    Lib(R.drawable.baseline_album_24,"Album"),
    Lib(R.drawable.baseline_playlist_play_24,"Song"),
    Lib(R.drawable.baseline_sports_martial_arts_24,"Genre")

)
