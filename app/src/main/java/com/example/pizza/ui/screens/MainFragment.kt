package com.example.pizza.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.example.pizza.R
import com.example.pizza.core.mapping.logcat
import com.example.pizza.core.mapping.toast
import com.example.pizza.databinding.FragmentMainBinding
import com.example.pizza.ui.adapters.AdvertisementAdapter
import com.example.pizza.ui.adapters.CategoryAdapter
import com.example.pizza.ui.adapters.FoodAdapter
import com.example.pizza.ui.adapters.OnCategoryClickListener
import com.example.pizza.ui.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : BaseFragment<FragmentMainBinding>(), OnCategoryClickListener {

    private val categoryAdapter = CategoryAdapter(this)
    private val advertisementAdapter = AdvertisementAdapter()

    override val viewModel by viewModels<MainViewModel> { factory }

    private val foodAdapter by lazy { FoodAdapter(viewModel::navigate) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerAdapters()
    }

    override fun onCategoryClick(position: Int) {
        viewModel.check(position)
    }

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun setObservers() {
        viewModel.viewModelScope.launch { observe() }

        viewModel.adds.observe(viewLifecycleOwner) {
            advertisementAdapter.items = it
        }

        viewModel.categories.observe(viewLifecycleOwner) {
            categoryAdapter.items = it
            categoryAdapter.notifyDataSetChanged()
        }
    }

    private fun setRecyclerAdapters() {
        binding.recyclerViewMeals.adapter = foodAdapter
        binding.recyclerViewCategories.adapter = categoryAdapter
        binding.recyclerViewAdvertisement.adapter = advertisementAdapter
    }

    private suspend fun observe() = withContext(Dispatchers.Main) {
        viewModel.data().observe(viewLifecycleOwner) {
            if (it == null) {
                showSnackbar(R.string.connection_error)
                toast(R.string.connection_error)
                logcat("error")
            }
            foodAdapter.items = it
        }
    }


}