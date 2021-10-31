package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.BR
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreatureRepository
import javax.inject.Inject

@HiltViewModel
class CreatureViewModel @Inject constructor(
    private val repository: CreatureRepository
) : ViewModel(), Observable {
    val number = MutableLiveData<Long>()

    private val _creature = MutableLiveData<Creature>()

    @get:Bindable
    val creature: LiveData<Creature>
        get() = _creature

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    private val composite = CompositeDisposable()

    fun loadCreature(creatureId: Long) {
        composite += repository
            .findByNumber(creatureId)
            .subscribe {
                _creature.postValue(it)

                notifyPropertyChanged(BR.creature)
            }
    }

    override fun addOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.remove(callback)
    }

    private fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}
