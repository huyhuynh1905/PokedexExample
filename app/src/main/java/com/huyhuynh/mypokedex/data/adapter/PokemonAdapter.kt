package com.huyhuynh.mypokedex.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.huyhuynh.mypokedex.data.model.Pokemon
import com.huyhuynh.mypokedex.databinding.ItemPokedexListBinding
import com.huyhuynh.mypokedex.BR

class PokemonAdapter(var items: List<Pokemon>, var onItemClickListener: OnItemClickListener? ) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemPokedexListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PokemonAdapter.ViewHolder, position: Int) {
        holder.binData(items[position], onItemClickListener)
    }

    class ViewHolder(var binding: ItemPokedexListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun binData(pokemon: Pokemon, onItemClickListener: OnItemClickListener?) {
            binding.apply {
                setVariable(BR.pokemon, pokemon)
                executePendingBindings()
            }
            binding.relativeLayoutBackground?.setOnClickListener {
                onItemClickListener?.onClickScan(pokemon)
            }
        }
    }

    fun setNewData(newItems: List<Pokemon>) {
        this.items = newItems
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClickScan(value: Pokemon)
        



    }

    


}