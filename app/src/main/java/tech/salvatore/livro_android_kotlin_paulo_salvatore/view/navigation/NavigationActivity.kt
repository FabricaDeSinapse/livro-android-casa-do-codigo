package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.navigation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreaturesViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.UserViewModel

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {
    private val creaturesViewModel: CreaturesViewModel by viewModels()

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)

        // TODO: Maybe would be better to observe only creatures' count instead of all list
        val creaturesCount = creaturesViewModel.creatures.value?.count() ?: 0

        val destination = when (creaturesCount) {
            0 -> R.id.creatures_choose_dest
            else -> R.id.creatures_list_dest
        }

        navGraph.setStartDestination(destination)

        navController.graph = navGraph
    }
}
