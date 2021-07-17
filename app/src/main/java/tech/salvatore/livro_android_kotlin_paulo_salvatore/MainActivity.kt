package tech.salvatore.livro_android_kotlin_paulo_salvatore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.salvatore.livro_android_kotlin_paulo_salvatore.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
