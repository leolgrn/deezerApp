package esgi.com.deezerApp.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import esgi.com.deezerApp.R
import esgi.com.deezerApp.utils.reactivex.RxBus
import esgi.com.deezerApp.utils.reactivex.RxEvent
import io.reactivex.disposables.Disposable


class RootActivity : AppCompatActivity() {

    private var player: Fragment? = null
    private lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        player = supportFragmentManager.findFragmentById(R.id.playerFragment)
        disposable = RxBus.listen(RxEvent.EventDisplayPlayer::class.java).subscribe {
            // TODO: Fragment transaction
        }

    }
}