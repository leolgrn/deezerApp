package esgi.com.deezerApp.common.navigator

import android.app.Activity
import esgi.com.deezerApp.albums.AlbumsActivity

enum class Route constructor(val activityClass: Class<out Activity>) {
    ALBUMS(AlbumsActivity::class.java)
}