package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding.CreaturesListFragmentBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreaturesViewModel

class CreaturesListFragment : Fragment() {

    companion object {
        fun newInstance() = CreaturesListFragment()
    }

    private var _binding: CreaturesListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreaturesViewModel by lazy {
        ViewModelProvider(this).get(CreaturesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            CreaturesListFragmentBinding.inflate(
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

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val items = listOf(
            Creature(1, 1, "Creature 01", "https://image.jpg", 1, 2, 3),
            Creature(2, 2, "Creature 02", "https://image.jpg", 3, 2, 1),
        )

        recyclerView.adapter =
            CreaturesListAdapter(items) {
                findNavController().navigate(R.id.creatures_view_dest, null)
            }

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}
