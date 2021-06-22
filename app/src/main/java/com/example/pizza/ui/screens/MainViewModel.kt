package com.example.pizza.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza.R
import com.example.pizza.core.DI.resources.ResourceProvider
import com.example.pizza.core.mapping.toMeal
import com.example.pizza.core.models.Advertisement
import com.example.pizza.core.models.Category
import com.example.pizza.core.models.Meal
import com.example.pizza.core.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val resources: ResourceProvider,
) : ViewModel() {

    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> get() = _meals

    private val _adds = MutableLiveData<List<Advertisement>>()
    val adds : LiveData<List<Advertisement>> get() = _adds

    private val _categories = MutableLiveData<List<Category>>()
    val category: LiveData<List<Category>> get() = _categories

    init {
        loadData()
    }

    private fun loadData() {
        getAdds()
        getCategories()
        getMeals()
    }

    private fun getMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = repository.getMeals()
            _meals.postValue(meals)
        }
    }

    private fun getCategories() {
        val cats = resources.array(R.array.categories)
        _categories.value = cats.map { Category(it) }
    }

    private fun getAdds() {
        _adds.value = listOf(
            Advertisement(
                resources.drawable(R.drawable.ic_advertisiment_sample_1)
            ),
            Advertisement(
                resources.drawable(R.drawable.ic_advertisiment_sample_2)
            ),
            Advertisement(
                resources.drawable(R.drawable.ic_advertisiment_sample_3)
            ),
            Advertisement(
                resources.drawable(R.drawable.ic_advertisiment_sample_1)
            ),
        )
    }


}