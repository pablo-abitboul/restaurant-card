package com.thefork.restaurantcard.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.thefork.domain.PicDiaporama
import com.thefork.domain.RestaurantDetail
import com.thefork.restaurantcard.R
import com.thefork.restaurantcard.databinding.ActivityMainBinding
import com.thefork.restaurantcard.utils.Status
import com.thefork.restaurantcard.viewmodel.RestaurantDetailViewModel
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: RestaurantDetailViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        viewModel.response.observe(this, Observer { newStatus ->
            bindStatus(newStatus.status)
        })

        viewModel.response.observe(this, Observer { newResponse ->
            if (newResponse.status == Status.SUCCESS) {
                newResponse.data?.let { bindCard(it) }
            }
        })

        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun configCarouselView(picsDiaporama: List<PicDiaporama>) {
        val list = mutableListOf<CarouselItem>()
        for (pic in picsDiaporama) {
            list.add(
                CarouselItem(
                    imageUrl = pic.pic,
                    caption = pic.label
                )
            )
        }
        binding.carouselView.addData(list)
    }

    private fun configRatingBubbles(avgRating: Double) {
        when (avgRating) {
            1.0 -> binding.star1.setImageResource(R.mipmap.ta_bubbles_full)
            1.5 -> {
                binding.star1.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star2.setImageResource(R.mipmap.ta_bubbles_half)
            }
            2.0 -> {
                binding.star1.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star2.setImageResource(R.mipmap.ta_bubbles_full)
            }
            2.5 -> {
                binding.star1.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star2.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star3.setImageResource(R.mipmap.ta_bubbles_half)
            }
            3.0 -> {
                binding.star1.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star2.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star3.setImageResource(R.mipmap.ta_bubbles_full)
            }
            3.5 -> {
                binding.star1.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star2.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star3.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star4.setImageResource(R.mipmap.ta_bubbles_half)
            }
            4.0 -> {
                binding.star1.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star2.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star3.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star4.setImageResource(R.mipmap.ta_bubbles_full)
            }
            4.5 -> {
                binding.star1.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star2.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star3.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star4.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star5.setImageResource(R.mipmap.ta_bubbles_half)
            }
            5.0 -> {
                binding.star1.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star2.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star3.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star4.setImageResource(R.mipmap.ta_bubbles_full)
                binding.star5.setImageResource(R.mipmap.ta_bubbles_full)
            }
        }
    }

    private fun bindStatus(status: Status?) {
        when (status) {
            Status.LOADING -> {
                binding.statusImage.visibility = View.VISIBLE
                binding.statusImage.setImageResource(R.mipmap.ic_loading)
                binding.linearRestaurantCard.visibility = View.GONE
            }
            Status.ERROR -> {
                binding.statusImage.visibility = View.VISIBLE
                binding.statusImage.setImageResource(R.mipmap.ic_error)
                binding.linearRestaurantCard.visibility = View.GONE

            }
            Status.SUCCESS -> {
                binding.statusImage.visibility = View.GONE
                binding.linearRestaurantCard.visibility = View.VISIBLE
            }
        }
    }

    private fun bindCard(newResponse: RestaurantDetail) {
        newResponse.picsDiaporama?.let { configCarouselView(it) }
        binding.restaurantName.text = newResponse.name
        binding.avgRate.text = newResponse.avgRate?.toString() ?: "0"
        binding.rateCount.text = "${newResponse.rateCount} ${getString(R.string.avis)}"
        binding.priceCard.text = "Prix moyen ${newResponse.cardPrice}€"
        binding.cardMain1.text = newResponse.cardMain1
        binding.cardMain2.text = newResponse.cardMain2
        binding.cardMain3.text = newResponse.cardMain3
        if (newResponse.chefName != null) {
            binding.chef.text =
                "${getString(R.string.extrait_de_la_carte_du)} ${newResponse.chefName}"
        }
        if (newResponse.priceCardMain1 == null) {
            binding.priceCardMain1.visibility = View.INVISIBLE
            binding.priceCardMain2.visibility = View.INVISIBLE
            binding.priceCardMain3.visibility = View.INVISIBLE
        } else {
            binding.priceCardMain1.text = "${newResponse.priceCardMain1}€"
            binding.priceCardMain2.text = "${newResponse.priceCardMain2}€"
            binding.priceCardMain3.text = "${newResponse.priceCardMain3}€"
        }
        binding.avgRate2.text = newResponse.avgRate?.toString() ?: "0"
        binding.taReviewCount.text = "${newResponse.taReviewCount} ${getString(R.string.avis)}"
        configRatingBubbles(newResponse.taAvgRating!!)
    }
}