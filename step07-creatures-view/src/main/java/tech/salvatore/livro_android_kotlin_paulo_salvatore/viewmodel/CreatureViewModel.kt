package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreaturesRepository
import javax.inject.Inject

@HiltViewModel
class CreatureViewModel @Inject constructor(
    private val creaturesRepository: CreaturesRepository,
) : ViewModel() {
    private val _creature = MutableLiveData<Creature>()

    val creature: LiveData<Creature>
        get() = _creature

    fun loadCreature(number: Int) {
        _creature.value = creaturesRepository.findCreature(number)
    }
}
