package com.example.musicappui

import androidx.annotation.DrawableRes

data class Lib(@DrawableRes val icon: Int, val name:String)

val libraries = listOf<Lib>(
    Lib(R.drawable.baseline_person_add_alt_1_24, "Playlist"),
    Lib(R.drawable.baseline_apps_24, "Artists"),
    Lib(R.drawable.ic_account, "Album"),
    Lib(R.drawable.ic_music_player_green, "Songs")
//    Lib(R.drawable.)
)