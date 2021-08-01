package com.example.pizza.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza.R
import com.example.pizza.core.DI.resources.ResourceProvider
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

    private val _adds = MutableLiveData<List<Advertisement>>()
    val adds: LiveData<List<Advertisement>> get() = _adds

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    init {
        loadData()
    }

    private fun loadData() {
        getAdds()
        getCategories()
        initialLoading()
    }

    // В данном методе проверяется какая категория использовалась при последнем запуске
    // Так как в приложение кэшируются продукты из этой последней категории
    // то я посчитал, что пользователю надо эту категорию соответственно подсвечивать
    private fun initialLoading() {
        val categoryStr: String? = repository.getLastCategory()
        categories.value?.let {
            // Срабабтывает только при самом первом запуске приложения
            if (categoryStr == null) {
                it[0].isChecked = true
                _categories.value = it
                getMeals(it[0])
            } // Во всех остальных случаях
            else {
                for (i in it.indices) {
                    if (it[i].name.equals(categoryStr)) {
                        it[i].isChecked = true
                        _categories.value = it
                        getMeals(it[i])
                        break
                    }
                }
            }
        }
    }

    private fun getMeals(category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            categories.value?.let {
                repository.getMeals(category)
            }

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

    fun data(): LiveData<List<Meal>> = repository.observeDB()

    fun check(position: Int) {
        _categories.value?.let { categories ->
            categories.map { it.isChecked = false }
            categories[position].isChecked = true
            _categories.value = categories

            val category = categories[position]
            getMeals(category)
        }
    }

}