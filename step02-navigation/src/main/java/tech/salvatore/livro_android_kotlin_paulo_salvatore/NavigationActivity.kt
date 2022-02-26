package tech.salvatore.livro_android_kotlin_paulo_salvatore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels()

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private val navController by lazy {
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)

        initNavigation()
    }

    private fun initNavigation() {
        // Add NavGraph
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph
    }
}
