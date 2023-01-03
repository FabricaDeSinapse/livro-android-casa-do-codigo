package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.evolving

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding.CreatureEvolvingFragmentBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreatureViewModel

@AndroidEntryPoint
class CreatureEvolvingFragment : Fragment() {
    private lateinit var binding: CreatureEvolvingFragmentBinding

    private val viewModel: CreatureViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            CreatureEvolvingFragmentBinding.inflate(
                layoutInflater,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
    }
}
