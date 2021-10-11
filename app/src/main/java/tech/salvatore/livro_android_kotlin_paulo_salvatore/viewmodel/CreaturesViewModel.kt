package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreatureRepository

class CreaturesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CreatureRepository(application)

    val creatures = MutableLiveData<List<Creature>>()

    // TODO: make only LiveData available outside ViewModel

    init {
        // TODO: remove subscription
        repository.creatures.subscribe {
            creatures.postValue(it)
        }
    }
}
