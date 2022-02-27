package tech.salvatore.livro_android_kotlin_paulo_salvatore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel.CreaturesViewModel

class CreaturesListFragment : Fragment() {
    private val creaturesViewModel: CreaturesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.creatures_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        creaturesViewModel.creatures.observe(viewLifecycleOwner) {
            // it = List<Creature>
        }
    }
}
