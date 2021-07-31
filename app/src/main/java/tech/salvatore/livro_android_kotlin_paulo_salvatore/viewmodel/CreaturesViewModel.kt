package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.lifecycle.ViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature

class CreaturesViewModel : ViewModel() {
    // TODO: Remove
    val text = "Text"

    val items = listOf(
        Creature(1, 1, "Creature 01", "https://image.jpg", 1, 2, 3),
        Creature(2, 2, "Creature 02", "https://image.jpg", 3, 2, 1),
    )
}
