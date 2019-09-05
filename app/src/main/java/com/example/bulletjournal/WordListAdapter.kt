package com.example.bulletjournal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bulletjournal.databinding.RecyclerviewItemBinding
import com.example.bulletjournal.enums.Word
import com.example.bulletjournal.enums.getDrawable
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class WordListAdapter(
    val onClick: (id: Long, state: Boolean) -> Unit,
    val onDelete: (word: Word) -> Unit
) : ListAdapter<Word, TypedViewHolder<RecyclerviewItemBinding>>(
    object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Word, newItem: Word) = oldItem == newItem
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
            val word = getItem(position)
            it.word = word
            it.drawable =
                ContextCompat.getDrawable(it.root.context, word.bullet.getDrawable(word.state))
            it.dateText =
                word.offsetDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
            it.root.setOnClickListener { onClick(word.id, word.state.not()) }

            it.executePendingBindings()
        }
    }

    fun delete(position: Int) {
        onDelete(getItem(position))
    }
}

class TypedViewHolder<T : ViewDataBinding>(view: View) : RecyclerView.ViewHolder(view) {
    val binding: T? = DataBindingUtil.bind(view)
}
