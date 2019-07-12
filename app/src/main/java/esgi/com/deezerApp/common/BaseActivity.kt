package esgi.com.deezerApp.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import esgi.com.deezerApp.common.navigator.Navigator
import esgi.com.deezerApp.utils.ClassUtils

@Suppress("UNCHECKED_CAST")
abstract class BaseActivity<P : BasePresenter<V>, V : BaseView>(@LayoutRes val layout: Int) : AppCompatActivity(),
    BaseView {

    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ClassUtils.instantiateTypeArgumentOf(javaClass)
        presenter.view = this as V
        presenter.navigator = Navigator(this)
        setContentView(layout)

        lifecycle.addObserver(presenter)
    }
}