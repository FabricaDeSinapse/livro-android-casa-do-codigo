package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding.CreaturesListFragmentBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreaturesViewModel
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.UsersViewModel

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

        viewModel.creatures.observe(this, {
            if (recyclerView.adapter == null) {
                recyclerView.adapter = CreaturesListAdapter(it) { creature ->
                    val action =
                        CreaturesListFragmentDirections.creaturesViewAction(creature.number)
                    findNavController().navigate(action, options)
                }

                return@observe
            }

            val adapter = recyclerView.adapter as CreaturesListAdapter

            // TODO: replace with notifyItemInserted
            adapter.setItems(it)
        })

//        recyclerView.adapter =
//            CreaturesListAdapter(viewModel.creatures) {
//                val action = CreaturesListFragmentDirections.creaturesViewAction(it.id)
//                findNavController().navigate(action, options)
//            }

        // TODO: Only for tests
        val usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        usersViewModel.users.observe(this, {
            Log.d("USER", it.count().toString())
        })
    }
}
