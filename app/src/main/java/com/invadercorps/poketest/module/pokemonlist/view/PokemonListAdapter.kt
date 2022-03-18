package com.invadercorps.poketest.module.pokemonlist.view

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.invadercorps.poketest.R
import com.invadercorps.poketest.data.repository.APIClient
import com.invadercorps.poketest.data.repository.model.Pokemon
import com.invadercorps.poketest.module.common.view.DrawableFactory
import java.util.Collections

class PokemonListAdapter(private val orientation: Int) : RecyclerView.Adapter<PokemonListAdapter.ListViewHolder>() {
    private lateinit var context: Context
    private var selectedPokemonId: String? = null
    private var listItems: List<Pokemon> = Collections.emptyList()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ListViewHolder {
        return ListViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) {
        val item = listItems[position]

        Glide
                .with(holder.pokemonImage)
                .load(APIClient.IMAGE_URL + item.id + ".png")
                .placeholder(DrawableFactory.makeCircularProgressDrawable(context))
                .into(holder.pokemonImage)

        holder.pokemonName.text = item.name
        highlightItem(item, holder)
    }

    /**
     * We must highlight selected item only on landscape mode for multi pane layout
    */
    private fun highlightItem(item: Pokemon, holder: ListViewHolder) {
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(selectedPokemonId == item.id)
                holder.itemView.setBackgroundColor(Color.GRAY)
            else
                holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    fun setContext(context: Context) {
        this.context = context
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    fun setSelectedPokemonId(pokemonId: String) {
        val oldSelectedPokemonPosition = listItems.indexOfFirst { it.id == selectedPokemonId }
        val selectedPokemonPosition = listItems.indexOfFirst { it.id == pokemonId }
        this.selectedPokemonId = pokemonId
        /*
        Instead of refresh all list with notifyDataSetChanged, we can
        update only old and current selected items
         */
        if(oldSelectedPokemonPosition != -1)
            notifyItemChanged(oldSelectedPokemonPosition)
        if(selectedPokemonPosition != -1)
            notifyItemChanged(selectedPokemonPosition)
    }

    fun setItems(listItems: List<Pokemon>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(@Nullable onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    inner class ListViewHolder(itemView: View) : ViewHolder(itemView),
        View.OnClickListener {
        var pokemonImage: ImageView
        var pokemonName: TextView
        override fun onClick(v: View?) {
            onItemClickListener?.onItemClick(listItems[adapterPosition].id)
        }

        init {
            itemView.setOnClickListener(this)
            pokemonImage = itemView.findViewById(R.id.pokemon_image)
            pokemonName = itemView.findViewById(R.id.pokemon_name)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(pokemonId: String)
    }
}