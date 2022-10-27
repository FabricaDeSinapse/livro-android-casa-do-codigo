package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val user = userRepository.user

    val onChooseCreature = userRepository.onChooseCreature

    private val composite = CompositeDisposable()

    fun chooseCreature() {
        composite += userRepository.chooseCreature().subscribe {
            Log.d("CHOOSE_CREATURE", it.toString())
        }
    }

    override fun onCleared() {
        composite.dispose()
    }
}
