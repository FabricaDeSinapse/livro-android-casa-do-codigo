package tech.salvatore.livro_android_kotlin_paulo_salvatore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.salvatore.livro_android_kotlin_paulo_salvatore.ui.creatures.CreaturesListFragment

class CreatureListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creature_list_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CreaturesListFragment.newInstance())
                .commitNow()
        }
    }
}
