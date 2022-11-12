package tech.salvatore.livro_android_kotlin_paulo_salvatore.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.salvatore.livro_android_kotlin_paulo_salvatore.R
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.Creature

class CreaturesListAdapter(
    private val items: List<Creature>,
) : RecyclerView.Adapter<CreaturesListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Creature) {
            val tvCreatureNumber = itemView.findViewById<TextView>(R.id.tvCreatureNumber)
            val tvCreatureName = itemView.findViewById<TextView>(R.id.tvCreatureName)

            tvCreatureNumber.text = item.number.toString()
            tvCreatureName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.creatures_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bindView(item)
    }

    override fun getItemCount() = items.count()
}
