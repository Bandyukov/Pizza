package com.example.pizza.ui.main

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.example.pizza.R
import com.example.pizza.core.models.Meal
import com.example.lib_architecture.base.ListItem
import com.example.pizza.core.mapping.default
import com.example.pizza.core.models.Advertisement
import com.example.pizza.core.models.Category
import com.example.pizza.databinding.ItemAdvertisementBinding
import com.example.pizza.databinding.ItemCategoryBinding
import com.example.pizza.databinding.ItemFoodBinding
import com.example.pizza.ui.adapters.OnCategoryClickListener
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object MainScreenDelegates {

    fun foodVerticalDelegate(navigate: () -> Unit) = adapterDelegateViewBinding<Meal, ListItem, ItemFoodBinding>(
        { inflater, container ->
            ItemFoodBinding.inflate(inflater, container, false)
        }
    ) {

        bind {
            val resources = binding.root.resources

            with(binding) {
                textViewFoodTitle.text = item.title
                textViewPrice.text = String.format(getString(R.string.price), item.price)

                Glide.with(root)
                    .applyDefaultRequestOptions(RequestOptions().default())
                    .load(item.imagePath)
                    .override(
                        resources.getDimensionPixelSize(R.dimen.food_image_width),
                        resources.getDimensionPixelSize(R.dimen.food_image_height)
                    )
                    .transform(
                        CenterCrop(),
                        RoundedCorners(resources.getDimensionPixelSize(R.dimen.food_image_radius))
                    )
                    .transition(withCrossFade())
                    .into(imageViewFoodIcon)

                imageViewFoodIcon.setOnClickListener {
                    navigate.invoke()
                }

                executePendingBindings()
            }
        }
    }

    fun categoryHorizontalDelegate(onCategoryClickListener: OnCategoryClickListener) =
        adapterDelegateViewBinding<Category, ListItem, ItemCategoryBinding>(
            { inflater, container ->
                ItemCategoryBinding.inflate(inflater, container, false)
            }
        ) {
            bind {

                val bgDefault = this.getDrawable(R.drawable.ic_category_bg)
                val bgChecked = this.getDrawable(R.drawable.ic_category_checked_bg)

                val brightPink = this.getColor(R.color.bright_pink)
                val lightGrey = this.getColor(R.color.light_grey)

                with(binding.textViewCategoryName) {
                    text = item.name

                    if (item.isChecked) {
                        background = bgChecked
                        setTextColor(brightPink)
                    } else {
                        background = bgDefault
                        setTextColor(lightGrey)
                    }

                    setOnClickListener {
                        onCategoryClickListener.onCategoryClick(adapterPosition)
                    }
                }

                binding.executePendingBindings()
            }
        }

    fun advertisementHorizontalDelegate() =
        adapterDelegateViewBinding<Advertisement, ListItem, ItemAdvertisementBinding>(
            { inflater, container ->
                ItemAdvertisementBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                Glide
                    .with(binding.root)
                    .load(item.image)
                    .transition(withCrossFade())
                    .into(binding.imageViewAdvertisement)
            }
        }
}