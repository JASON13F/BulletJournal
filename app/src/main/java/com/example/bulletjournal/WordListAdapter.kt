package com.example.bulletjournal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bulletjournal.databinding.RecyclerviewItemBinding
import com.example.bulletjournal.enums.Word

class WordListAdapter : ListAdapter<Word, TypedViewHolder<RecyclerviewItemBinding>>(
    object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TypedViewHolder<RecyclerviewItemBinding>(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TypedViewHolder<RecyclerviewItemBinding>, position: Int) {
        holder.binding?.let {
            it.word.text = getItem(position).word

            it.executePendingBindings()
        }
    }
}

class TypedViewHolder<T : ViewDataBinding>(view: View) : RecyclerView.ViewHolder(view) {
    val binding: T? = DataBindingUtil.bind(view)
}
