package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreaturesViewModel

@AndroidEntryPoint
class CreaturesListFragment : Fragment() {
    private val creaturesViewModel: CreaturesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.creatures_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        creaturesViewModel.creatures.observe(viewLifecycleOwner) {
            recyclerView.adapter = CreaturesListAdapter(it) { creature ->
                if (!creature.isKnown) {
                    return@CreaturesListAdapter
                }

                val action =
                    CreaturesListFragmentDirections
                        .creatureViewAction(creature.number)

                findNavController().navigate(action)
            }
        }
    }
}
