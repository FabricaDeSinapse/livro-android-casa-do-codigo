package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(
    userRepository: UserRepository,
) : ViewModel() {
    val creatures = MutableLiveData<List<Creature>>()

    private val composite = CompositeDisposable()

    init {
        composite += userRepository.allCreatures.subscribe {
            creatures.value = it
        }
    }

    override fun onCleared() {
        composite.dispose()
    }
}
