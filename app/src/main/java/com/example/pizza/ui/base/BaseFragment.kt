package com.example.pizza.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.pizza.core.DI.viewModel.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    protected lateinit var factory: ViewModelProviderFactory

    abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
    }

    abstract fun setObservers()

    @LayoutRes
    abstract fun getLayoutId() : Int

    protected fun showToast(text: String) =
        Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()

    protected fun showToastLong(text: String) =
        Toast.makeText(this.context, text, Toast.LENGTH_LONG).show()

    protected fun showSnackbar(text: String) =
        view?.let { Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show() }

    protected fun showSnackbar(@StringRes text: Int) =
        view?.let { Snackbar.make(it, text, Snackbar.LENGTH_SHORT).show() }


}