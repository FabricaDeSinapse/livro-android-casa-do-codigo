package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreaturesRepository
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserCreatureRepository
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class CreatureViewModel @Inject constructor(
        private val creaturesRepository: CreaturesRepository,
        private val userCreatureRepository: UserCreatureRepository,
        private val userRepository: UserRepository,
) : ViewModel() {
    val number = MutableLiveData<Long>()

    private val _creature = MutableLiveData<Creature>()

    val creature: LiveData<Creature>
        get() = _creature

    private val composite = CompositeDisposable()

    fun loadCreature(creatureId: Long) {
        composite += creaturesRepository
                .findByNumber(creatureId)
                .subscribe {
                    _creature.postValue(it)
                }
    }

    val feed: Function0<Unit> = {
        composite += userRepository.user
                .flatMapSingle {
                    userCreatureRepository.feed(it.id, creature.value!!)
                }.doOnNext {
                    Log.d("CREATURE_VIEW_MODEL", "Criatura atualizada")
                }.subscribe {
                    _creature.postValue(it)
                }
        // TODO: Talvez aplicar dispose direto?
//                .dispose()
    }

    /*
    feed creature
    add exp to creature (update on db)
    set lastFeed as current time
    */

    val play: Function0<Unit> = {
        Log.d("CREATURE", "Play with creature")
    }

    val train: Function0<Unit> = {
        Log.d("CREATURE", "Train creature")
    }
}
