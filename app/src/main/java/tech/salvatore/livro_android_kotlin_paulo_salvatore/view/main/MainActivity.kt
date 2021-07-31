package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.Theme_AppCompat_NoActionBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}
