package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreatureRepository

class CreatureViewModel(application: Application) : AndroidViewModel(application) {
    val id = MutableLiveData<Int>()
    val item = MutableLiveData<Creature>()

    private val repository = CreatureRepository(application)

    // TODO: Add data
    val creatures = listOf<Creature>()
}
