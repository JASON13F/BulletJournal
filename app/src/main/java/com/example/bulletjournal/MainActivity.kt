package com.example.bulletjournal

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.example.bulletjournal.database.WordRoomDatabase
import com.example.bulletjournal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.init(WordRoomDatabase.getDatabase(this).wordDao())

        val adapter = WordListAdapter { id, state -> viewModel.update(id, state) }
        binding.recyclerView.adapter = adapter

        viewModel.allWords.observe(this, adapter::submitList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                viewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
