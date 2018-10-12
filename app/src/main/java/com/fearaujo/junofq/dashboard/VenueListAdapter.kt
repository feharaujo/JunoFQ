package com.fearaujo.junofq.dashboard

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fearaujo.junofq.R
import com.fearaujo.junofq.util.inflate
import com.fearaujo.model.Venue

class VenueListAdapter(private val listener: VenueClickListener) : RecyclerView.Adapter<VenueListAdapter.ViewHolder>() {

    private val items = mutableListOf<Venue>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.list_item))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(newItems: List<Venue>) {
        this.items.clear()
        this.items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.title) as TextView
        private val address: TextView = itemView.findViewById(R.id.address) as TextView
        private val rating: TextView = itemView.findViewById(R.id.rating) as TextView

        fun bind(item: Venue) {
            title.text = item.name
            address.text = "${item.location?.address} - ${item.location?.city}"
            rating.text = item.likes?.count?.toString()

            itemView.setOnClickListener {
                listener.onSelectItem(item)
            }
        }
    }
}