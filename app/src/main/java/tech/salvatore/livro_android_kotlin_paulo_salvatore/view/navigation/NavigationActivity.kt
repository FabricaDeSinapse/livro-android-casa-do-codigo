package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.navigation

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.list.CreaturesListFragmentDirections
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.NavigationViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.UserViewModel

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels()

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)

        val content = findViewById<View>(android.R.id.content)

        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    val isReady = navigationViewModel.isReady.value != null

                    if (isReady && initNavigation()) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)

                        return true
                    }

                    return false
                }
            }
        )
    }

    private fun initNavigation(): Boolean {
        // Load NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Add NavGraph
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph

        // Make sure user is available beforing releasing splash screen
        val user = userViewModel.user.value ?: return false

        // Check if any creatures are available and navigate to choose creatures screen
        if (user.newCreaturesAvailable > 0
            && navController.currentDestination?.id != R.id.creatures_choose_dest
        ) {
            val action = CreaturesListFragmentDirections.creaturesChooseAction()
            navController.navigate(action)
        }

        // Finish navigation init
        return true
    }
}
