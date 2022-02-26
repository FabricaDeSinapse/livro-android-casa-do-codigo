package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.added

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding. CreatureAddedFragmentBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.view.CreatureViewFragmentArgs
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreatureViewModel

@AndroidEntryPoint
class CreatureAddedFragment : Fragment() {
    private lateinit var binding: CreatureAddedFragmentBinding

    private val viewModel: CreatureViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            CreatureAddedFragmentBinding.inflate(
                layoutInflater,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btAddCreature = view.findViewById<Button>(R.id.btAddCreature)
        btAddCreature.setOnClickListener {
            findNavController().popBackStack()
        }

        val safeArgs: CreatureViewFragmentArgs by navArgs()
        val creatureId = safeArgs.creatureNumber

        viewModel.loadCreature(creatureId)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel
    }
}
