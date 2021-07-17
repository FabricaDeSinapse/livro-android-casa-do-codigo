package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.added

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R

class CreaturesAddedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creatures_added_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CreaturesAddedFragment.newInstance())
                .commitNow()
        }
    }
}
