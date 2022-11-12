package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreaturesRepository
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(
    private val creaturesRepository: CreaturesRepository,
    userRepository: UserRepository,
) : ViewModel() {
    val creatures = MutableLiveData(userRepository.allCreatures)

    fun findCreature(number: Int) = creaturesRepository.findCreature(number)
}
