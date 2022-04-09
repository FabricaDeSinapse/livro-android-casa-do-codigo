package tech.salvatore.livro_android_kotlin_paulo_salvatore.view.creatures

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.databinding.CreaturesListItemBinding
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature

class CreaturesListAdapter(
    private val items: List<Creature>,
) : RecyclerView.Adapter<CreaturesListAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: CreaturesListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: Creature) {
            binding.creature = item

            val tvCreatureNumber = itemView.findViewById<TextView>(R.id.tvCreatureNumber)
            tvCreatureNumber.text = item.number.toString()

            val ivCreature = itemView.findViewById<ImageView>(R.id.ivCreature)
            Glide.with(ivCreature).load(item.imageUrl).into(ivCreature)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = CreaturesListItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    override fun getItemCount() = items.count()
}
