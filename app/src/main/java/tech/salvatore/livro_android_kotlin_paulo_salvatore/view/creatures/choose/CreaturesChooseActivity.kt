package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.choose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R

class CreaturesChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creatures_choose_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CreaturesChooseFragment.newInstance())
                .commitNow()
        }
    }
}
