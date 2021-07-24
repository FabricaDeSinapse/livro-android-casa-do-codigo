package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding.CreaturesViewFragmentBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreaturesViewModel

class CreaturesViewFragment : Fragment() {

    companion object {
        fun newInstance() = CreaturesViewFragment()
    }

    private var _binding: CreaturesViewFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreaturesViewModel by lazy {
        ViewModelProvider(this).get(CreaturesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            CreaturesViewFragmentBinding.inflate(
                layoutInflater,
                container,
                false
            )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
    }
}
