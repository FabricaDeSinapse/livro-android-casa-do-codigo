package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreaturesRepository
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class CreaturesViewModel @Inject constructor(
    application: Application,
    repository: CreaturesRepository,
    userRepository: UserRepository,
) : AndroidViewModel(application) {
    private val _creatures = MutableLiveData<List<Creature>>()

    val creatures: LiveData<List<Creature>>
        get() = _creatures

    private val _creaturesOwnByUser = MutableLiveData<List<Creature?>>()

    val creaturesOwnByUser: LiveData<List<Creature?>>
        get() = _creaturesOwnByUser

    private val composite = CompositeDisposable()

    init {
        // Update creatures
        composite += repository.creatures.subscribe {
            _creatures.postValue(it)
        }

        // Update creatures own by user
        composite += Observable.combineLatest(
            userRepository.user,
            repository.creatures
        ) { user, creatures ->
            creatures.map { creature ->
                user.creatures.find { userCreature ->
                    creature.number == userCreature.number
                }
            }
        }.subscribe {
            _creaturesOwnByUser.postValue(it)
        }
    }

    override fun onCleared() {
        composite.dispose()
    }
}
