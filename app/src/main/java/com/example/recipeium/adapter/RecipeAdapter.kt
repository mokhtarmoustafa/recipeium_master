package com.example.recipeium.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.recipeium.databinding.RecipeListItemBinding
import com.example.recipeium.models.Result

class RecipeAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return RecipeViewHolder(
            RecipeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecipeViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Result>) {
        differ.submitList(list)
    }

    class RecipeViewHolder
    constructor(
      val   binding: RecipeListItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Result) = with(binding) {
            binding.recipe=item
            itemView.setOnClickListener {
                interaction?.onItemSelected(bindingAdapterPosition, item)
            }

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Result)
    }
}