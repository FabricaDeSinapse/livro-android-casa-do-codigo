package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserCreatureRepository
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class CreatureViewModel @Inject constructor(
    private val userCreatureRepository: UserCreatureRepository,
    private val userRepository: UserRepository,
) : ViewModel() {
    private val _creature = MutableLiveData<Creature>()

    val creature: LiveData<Creature>
        get() = _creature

    private val composite = CompositeDisposable()

    fun loadCreature(creatureNumber: Long) {
        composite += userRepository.user
            .take(1)
            .flatMapSingle {
                userCreatureRepository.findByUserIdAndCreatureNumber(it.id, creatureNumber)
            }
            .subscribe {
                _creature.postValue(it)
            }
    }

    val feed: Function0<Unit> = {
        composite += userRepository.user
            .take(1)
            .flatMapSingle {
                userCreatureRepository.feed(it.id, creature.value!!)
            }.subscribe {
                _creature.postValue(it)
            }
    }

    val train: Function0<Unit> = {
        composite += userRepository.user
            .take(1)
            .flatMapSingle {
                userCreatureRepository.train(it.id, creature.value!!)
            }.subscribe {
                _creature.postValue(it)
            }
    }

    val play: Function0<Unit> = {
        composite += userRepository.user
            .take(1)
            .flatMapSingle {
                userCreatureRepository.play(it.id, creature.value!!)
            }.subscribe {
                _creature.postValue(it)
            }
    }

    fun evolve() {
        composite += userRepository.user
            .take(1)
            .flatMapSingle {
                userCreatureRepository.evolve(it.id, creature.value!!)
            }.subscribe {
                _creature.postValue(it)
            }
    }
}
