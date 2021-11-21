package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreatureRepository
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserCreatureRepository
import javax.inject.Inject

@HiltViewModel
class CreatureViewModel @Inject constructor(
        private val creatureRepository: CreatureRepository,
        private val userCreatureRepository: UserCreatureRepository
) : ViewModel() {
    val number = MutableLiveData<Long>()

    private val _creature = MutableLiveData<Creature>()

    val creature: LiveData<Creature>
        get() = _creature

    private val composite = CompositeDisposable()

    fun loadCreature(creatureId: Long) {
        composite += creatureRepository
                .findByNumber(creatureId)
                .subscribe {
                    _creature.postValue(it)
                }
    }

    val feed: Function0<Unit> = {
        Log.d("CREATURE", "Feed creature")
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
