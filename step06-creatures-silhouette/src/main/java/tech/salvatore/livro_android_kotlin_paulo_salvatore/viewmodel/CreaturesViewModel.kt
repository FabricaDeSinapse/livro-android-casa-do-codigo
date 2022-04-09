package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreaturesRepository
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(
    private val creaturesRepository: CreaturesRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    val creatures = MutableLiveData<List<Creature>>()

    init {
        creatures.value = creaturesRepository.creatures.map {
            val isKnown = userRepository.user.creatures.any { creatureOwnByUser ->
                creatureOwnByUser.number == it.number
            }

            it.copy(
                name = if (isKnown) it.name else "?????",
                isKnown = isKnown
            )
        }
    }

    fun findCreature(number: Int) = creaturesRepository.findCreature(number)
}
