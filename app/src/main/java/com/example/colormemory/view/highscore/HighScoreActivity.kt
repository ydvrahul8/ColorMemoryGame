package com.example.colormemory.view.highscore

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.colormemory.R
import com.example.colormemory.db.UserDatabase
import com.example.colormemory.models.User
import com.example.colormemory.repository.UserRepository
import com.example.colormemory.utils.VerticalSpacingItemDecoration
import com.example.colormemory.view.common.adapter.HighScoreAdapter
import com.example.colormemory.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_high_score.*
import javax.inject.Inject

class HighScoreActivity : DaggerAppCompatActivity() {

    private lateinit var highScoreViewModel: HighScoreViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: HighScoreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)
        setUpToolbar()

        highScoreViewModel = ViewModelProvider(this, viewModelProviderFactory).get(HighScoreViewModel::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        highScoreViewModel.allUsers.observe(this, Observer {
            Log.e("HighScoreActivity", "subscribeObservers: " + it.size.toString())
            if (it.isNullOrEmpty()) {
                recyclerView.visibility = View.GONE
                textView_noData.visibility = View.VISIBLE
            } else {
                adapter.setItems(it as ArrayList<User>)
                recyclerView.addItemDecoration(VerticalSpacingItemDecoration(12))
                recyclerView.adapter = adapter
            }
        })
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}