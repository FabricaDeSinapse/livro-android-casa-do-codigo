package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.evolving

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R

class CreaturesEvolvingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creatures_evolving_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CreaturesEvolvingFragment.newInstance())
                .commitNow()
        }
    }
}
