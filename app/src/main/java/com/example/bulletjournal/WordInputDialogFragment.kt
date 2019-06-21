package com.example.bulletjournal

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.bulletjournal.databinding.DialogNewWordBinding
import com.example.bulletjournal.enums.Word

class WordInputDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
        val binding = DataBindingUtil.inflate<DialogNewWordBinding>(
            requireActivity().layoutInflater, R.layout.dialog_new_word, null, false
        )

        return AlertDialog.Builder(requireActivity())
            .setTitle(getString(R.string.main_dialog_title))
            .setView(binding.root)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                viewModel.insert(Word(word = binding.editWord.text.toString()))
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create()
    }

    companion object {
        fun newInstance() = WordInputDialogFragment()
    }
}
