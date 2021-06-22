package com.example.pizza.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.pizza.R
import com.example.pizza.core.DI.viewModel.ViewModelProviderFactory
import com.example.pizza.databinding.FragmentMainBinding
import com.example.pizza.ui.adapters.AdvertisementAdapter
import com.example.pizza.ui.adapters.CategoryAdapter
import com.example.pizza.ui.adapters.FoodAdapter
import com.example.pizza.ui.adapters.OnCategoryClickListener
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class MainFragment : DaggerFragment(), OnCategoryClickListener {

    private lateinit var binding: FragmentMainBinding

    private val foodAdapter = FoodAdapter()
    private val categoryAdapter = CategoryAdapter(this)
    private val advertisementAdapter = AdvertisementAdapter()

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private val viewModel by viewModels<MainViewModel> { factory }

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

        setRecyclerAdapters()

        setObservers()

        return binding.root
    }

    override fun onCategoryClick(position: Int) {
        viewModel.check(position)
    }

    private fun setRecyclerAdapters() {
        binding.recyclerViewMeals.adapter = foodAdapter
        binding.recyclerViewCategories.adapter = categoryAdapter
        binding.recyclerViewAdvertisement.adapter = advertisementAdapter
    }

    private fun setObservers() {
        viewModel.meals.observe(viewLifecycleOwner) {
            if (it.isEmpty())
                Snackbar.make(binding.root, getString(R.string.connection_error), Snackbar.LENGTH_SHORT).show()
            foodAdapter.items = it
        }

        viewModel.adds.observe(viewLifecycleOwner) {
            advertisementAdapter.items = it
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.items = it
            categoryAdapter.notifyDataSetChanged()
        }
    }
}