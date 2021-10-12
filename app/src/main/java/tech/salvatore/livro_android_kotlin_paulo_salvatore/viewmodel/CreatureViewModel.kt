package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature

class CreatureViewModel : ViewModel() {
    val id = MutableLiveData<Long>()
    val item = MutableLiveData<Creature>()
}
