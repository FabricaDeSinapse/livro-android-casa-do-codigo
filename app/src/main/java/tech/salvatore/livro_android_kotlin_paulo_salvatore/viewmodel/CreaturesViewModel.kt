package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreatureRepository

class CreaturesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CreatureRepository(application)

    private val _creatures = MutableLiveData<List<Creature>>()

    val creatures: LiveData<List<Creature>>
        get() = _creatures

    private val composite = CompositeDisposable()

    init {
        composite += repository.creatures.subscribe {
            _creatures.postValue(it)
        }
    }

    override fun onCleared() {
        composite.dispose()
    }
}
