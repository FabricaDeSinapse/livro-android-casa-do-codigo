package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreaturesViewModel

class NavigationActivity : AppCompatActivity() {
    private val creaturesViewModel: CreaturesViewModel by lazy {
        ViewModelProvider(this).get(CreaturesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.Theme_AppCompat_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)

        // TODO: Maybe would be better to observe only creatures' count instead of all list
        creaturesViewModel.creatures.observe(this, {
            val destination = when (it.count()) {
                0 -> R.id.creatures_choose_dest
                else -> R.id.creatures_list_dest
            }

            navGraph.setStartDestination(destination)

            navController.graph = navGraph
        })
    }
}
