package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding.CreaturesListFragmentBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreaturesViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.UserViewModel

@AndroidEntryPoint
class CreaturesListFragment : Fragment() {
    private lateinit var binding: CreaturesListFragmentBinding

    private val creaturesViewModel: CreaturesViewModel by viewModels()

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            CreaturesListFragmentBinding.inflate(
                layoutInflater,
                container,
                false
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userViewModel = userViewModel

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val options = navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }

        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        creaturesViewModel.creaturesOwnByUser.observe(viewLifecycleOwner) {
            if (recyclerView.adapter == null) {
                recyclerView.adapter = CreaturesListAdapter(it) { creature ->
                    if (!creature.isKnown) {
                        return@CreaturesListAdapter
                    }

                    val action =
                        CreaturesListFragmentDirections
                            .creatureViewAction(creature.number)

                    findNavController().navigate(action, options)
                }

                return@observe
            }

            val adapter = recyclerView.adapter as CreaturesListAdapter

            // TODO: replace with notifyItemInserted
            adapter.setItems(it)
        }
    }
}
