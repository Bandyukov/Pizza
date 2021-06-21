package com.example.pizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pizza.R
import com.example.pizza.core.models.Advertisement
import com.example.pizza.core.models.Category
import com.example.pizza.core.models.Food
import com.example.pizza.databinding.FragmentMainBinding
import com.example.pizza.ui.adapters.AdvertisementAdapter
import com.example.pizza.ui.adapters.CategoryAdapter
import com.example.pizza.ui.adapters.FoodAdapter
import com.example.pizza.ui.adapters.OnCategoryClickListener


class MainFragment : Fragment(), OnCategoryClickListener {

    private lateinit var binding: FragmentMainBinding

    private var cats = listOf<Category>()

    private val foodAdapter = FoodAdapter()
    private val categoryAdapter = CategoryAdapter(this)
    private val advertisementAdapter = AdvertisementAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main,
            container,
            false
        )

        binding.recyclerViewMeals.adapter = foodAdapter
        binding.recyclerViewCategories.adapter = categoryAdapter
        binding.recyclerViewAdvertisement.adapter = advertisementAdapter

        val meals = listOf<Food>(
            Food(
                "Ветчина и грибы",
                "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
                "от 650р"
            ),
            Food(
                "Ананас",
                "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
                "от 380р"
            ),
            Food(
                "Марго",
                "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
                "от 950р"
            ),
            Food(
                "Салями",
                "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
                "от 311р"
            ),
            Food(
                "Ветчина",
                "Ветчина,шампиньоны, увеличинная порция моцареллы, томатный соус",
                "от 450р"
            ),
        )

        cats = listOf(
            Category("Пицца"), Category("Макароны"), Category("Суши"), Category("Ролы"),
            Category("Алкоголь"), Category("Десерты"), Category("Напитки"), Category("Фрукты"),
        )

        val adds = listOf(Advertisement(resources.getDrawable(R.drawable.adver_sample)),
            Advertisement(resources.getDrawable(R.drawable.adver_sample_2)),
            Advertisement(resources.getDrawable(R.drawable.adver_sample)),
            Advertisement(resources.getDrawable(R.drawable.adver_sample_2)),)



        foodAdapter.items = meals
        categoryAdapter.items = cats
        advertisementAdapter.items = adds

        return binding.root
    }

    override fun onCategoryClick(position: Int) {
        cats[position].isChecked = !cats[position].isChecked
    }
}