package tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val creaturesRepository: CreaturesRepository,
) {
    val user = User("Paulo Salvatore", true)

    val allCreatures: Observable<List<Creature>>
        get() = creaturesRepository.creatures.map { list ->
            list.map {
                val isKnown = user.creatures.any { creatureOwnByUser ->
                    creatureOwnByUser.number == it.number
                }

                it.copy(
                    name = if (isKnown) it.name else "?????",
                    isKnown = isKnown
                )
            }
        }

    private val _onChooseCreature = MutableLiveData<Creature>()
    val onChooseCreature: LiveData<Creature>
        get() = _onChooseCreature

    fun chooseCreature() {
        if (!user.hasCreatureAvailable) {
            return
        }

        user.hasCreatureAvailable = false

        creaturesRepository.creatures.subscribe {
            val randomCreature = it.random()
            user.creatures.add(randomCreature)

            _onChooseCreature.value = randomCreature
        }.dispose()
    }
}