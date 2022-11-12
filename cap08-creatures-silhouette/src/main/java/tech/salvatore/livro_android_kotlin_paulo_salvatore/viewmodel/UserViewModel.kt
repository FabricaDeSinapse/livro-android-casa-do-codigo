package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {
    val user = userRepository.user

    val onChooseCreature = userRepository.onChooseCreature

    fun chooseCreature() {
        userRepository.chooseCreature()
    }
}
