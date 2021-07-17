package tech.salvatore.livro_android_kotlin_paulo_salvatore.ui.creatures

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R

class CreaturesListFragment : Fragment() {

    companion object {
        fun newInstance() = CreaturesListFragment()
    }

    private lateinit var viewModel: CreaturesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreaturesViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
