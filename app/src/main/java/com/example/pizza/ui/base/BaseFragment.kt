package com.example.pizza.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.pizza.core.DI.viewModel.ViewModelProviderFactory
import com.example.pizza.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding> : DaggerFragment() {

    @Inject
    protected lateinit var factory: ViewModelProviderFactory

    protected lateinit var binding: T

    abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )
        setObservers()

        return binding.root
    }

    abstract fun setObservers()

    @LayoutRes
    abstract fun getLayoutId() : Int

    private fun showToast(text: String) =
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
    private fun showToastLong(text: String) =
        Toast.makeText(this.context, text, Toast.LENGTH_LONG).show()

    protected fun showToast(@StringRes stringId: Int) = showToast(getString(stringId))
    protected fun showToastLong(@StringRes stringId: Int) = showToastLong(getString(stringId))


    private fun showSnackbar(text: String) =
        view?.let { Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show() }

    protected fun showSnackbar(@StringRes stringId: Int) = showSnackbar(getString(stringId))


}