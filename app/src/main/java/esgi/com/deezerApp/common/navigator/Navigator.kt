package esgi.com.deezerApp.common.navigator

import android.app.Activity

class Navigator(activity: Activity) : BaseNavigator(activity) {
    fun goToAlbums() = goTo(Route.ALBUMS)
}