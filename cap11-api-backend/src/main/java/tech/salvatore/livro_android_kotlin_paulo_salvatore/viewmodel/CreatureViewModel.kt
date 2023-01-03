package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
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

    private val composite = CompositeDisposable()

    fun loadCreature(number: Int) {
        composite += creaturesRepository.findCreature(number).subscribe {
            _creature.value = it
        }
    }

    override fun onCleared() {
        composite.dispose()
    }
}
