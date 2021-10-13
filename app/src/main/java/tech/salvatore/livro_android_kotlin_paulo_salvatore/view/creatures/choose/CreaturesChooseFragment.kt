package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.choose

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding.CreaturesChooseFragmentBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreaturesViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.UserViewModel

@AndroidEntryPoint
class CreaturesChooseFragment : Fragment() {
    private lateinit var binding: CreaturesChooseFragmentBinding

    private val viewModel: CreaturesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            CreaturesChooseFragmentBinding.inflate(
                layoutInflater,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel

        // TODO: Only for tests
        val usersViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        usersViewModel.user.observe(this, {
            Log.d("USER", it.toString())
        })
    }
}
