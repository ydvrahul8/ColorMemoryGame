package com.example.colormemory.view.home

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.colormemory.R
import com.example.colormemory.databinding.ActivityHomeBinding
import com.example.colormemory.db.UserDatabase
import com.example.colormemory.models.CardDetails
import com.example.colormemory.models.ColorCards
import com.example.colormemory.models.User
import com.example.colormemory.utils.OnItemClickListener
import com.example.colormemory.utils.Resource
import com.example.colormemory.utils.Status
import com.example.colormemory.view.common.adapter.MyImageAdapter
import com.example.colormemory.view.common.dialog.RegisterDialog
import com.example.colormemory.view.highscore.HighScoreActivity
import com.example.colormemory.view.highscore.HighScoreViewModel
import com.example.colormemory.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity(),
    OnItemClickListener {

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var adapter: MyImageAdapter

    @Inject
    lateinit var viewModelProvideFactory: ViewModelProviderFactory

    private var firstCards: CardDetails? = null
    private var secondCards: CardDetails? = null
    private lateinit var viewModel: HomeViewModel
    private lateinit var highScoreViewModel: HighScoreViewModel
    private var cardsMatched = HashSet<Int>()
    private var score: Int? = null
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        viewModel = ViewModelProvider(this, viewModelProvideFactory).get(HomeViewModel::class.java)

        binding.homeViewModel = viewModel
        binding.lifecycleOwner = this

        highScoreViewModel =
            ViewModelProvider(this, viewModelProvideFactory).get(HighScoreViewModel::class.java)

        binding.textViewHighScore.setOnClickListener {
            startActivity(Intent(this, HighScoreActivity::class.java))
        }
        init()
    }

    private fun init() {
        adapter.setData(ColorCards.getData(), this)
        binding.recyclerView.adapter = adapter
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.firstCardDetails.observe(this, Observer {
            handleCard(it)
        })

        viewModel.secondCardDetails.observe(this, Observer {
            handleCard(it)
        })

        viewModel.score.observe(this, Observer {
            score = it
        })
        highScoreViewModel.insertState.observe(this, Observer {
            if (it > -1)
                Toast.makeText(this, "High Score registered successfully", Toast.LENGTH_SHORT)
                    .show()
            else
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show()

        })
    }

    private fun handleCard(it: Resource<CardDetails>?) {
        val cardDetails = it?.data
        when (it?.status) {
            Status.MATCHED -> {
                requestManager.load(R.drawable.white_bg).into(cardDetails!!.imageView)
                cardsMatched.add(cardDetails.index)
                if (cardsMatched.size == 16) {
                    registerUser()
                }
            }
            Status.UNMATCHED -> {
                requestManager.load(R.drawable.card_bg).into(cardDetails!!.imageView)
            }
        }
    }

    private fun registerUser() {
        RegisterDialog.Builder()
            .okButtonClick { name ->
                highScoreViewModel.insert(User(0, name, score!!))
                reset()
            }
            .cancelButtonClick {
                init()
            }
            .isCancellable(false)
            .build().show(supportFragmentManager, "")
    }

    private fun reset() {
        viewModel.resetScore()
        cardsMatched.clear()
        init()
    }


    override fun onItemClick(index: Int, cards: ColorCards, view: ImageView) {
        if (!cardsMatched.contains(index)) {
            requestManager.load(cards.backgroundImage).into(view)
            if (firstCards == null) {
                firstCards = CardDetails(
                    index,
                    cards,
                    view
                )
            } else {
                secondCards = CardDetails(
                    index,
                    cards,
                    view
                )
                viewModel.checkCards(firstCards, secondCards)
                firstCards = null
                secondCards = null
            }
        }
    }

}