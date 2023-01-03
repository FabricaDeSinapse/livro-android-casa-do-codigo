package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding.CreatureViewFragmentBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreatureViewModel
import kotlin.concurrent.fixedRateTimer

@AndroidEntryPoint
class CreatureViewFragment : Fragment() {
    private lateinit var binding: CreatureViewFragmentBinding

    private val viewModel: CreatureViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            CreatureViewFragmentBinding.inflate(
                layoutInflater,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs: CreatureViewFragmentArgs by navArgs()
        val creatureNumber = safeArgs.creatureNumber

        viewModel.loadCreature(creatureNumber)

        binding.viewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        fixedRateTimer(period = 1000L) {
            binding.invalidateAll()
        }
    }
}
