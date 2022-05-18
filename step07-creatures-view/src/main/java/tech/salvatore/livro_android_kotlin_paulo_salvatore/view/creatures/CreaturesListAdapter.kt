package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding.CreaturesListItemBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature

class CreaturesListAdapter(
    private val items: List<Creature>,
    private val listener: (Creature) -> Unit,
) : RecyclerView.Adapter<CreaturesListAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: CreaturesListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(
            item: Creature,
            listener: (Creature) -> Unit,
        ) {
            binding.creature = item

            itemView.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = CreaturesListItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item, listener)
    }

    override fun getItemCount() = items.count()
}
