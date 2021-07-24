package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.added

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding.CreaturesAddedFragmentBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreaturesViewModel

class CreaturesAddedFragment : Fragment() {

    companion object {
        fun newInstance() = CreaturesAddedFragment()
    }

    private var _binding: CreaturesAddedFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CreaturesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            CreaturesAddedFragmentBinding.inflate(
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
        viewModel = ViewModelProvider(this).get(CreaturesViewModel::class.java)
    }
}
