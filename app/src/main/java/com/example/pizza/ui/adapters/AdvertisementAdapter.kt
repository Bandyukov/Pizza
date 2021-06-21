package com.example.pizza.ui.adapters

import com.example.pizza.core.base.BaseDiffUtilItemCallback
import com.example.pizza.core.base.ListItem
import com.example.pizza.ui.main.MainScreenDelegates
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class AdvertisementAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {
    init {
        delegatesManager.addDelegate(MainScreenDelegates.advertisementHorizontalDelegate())
    }
}