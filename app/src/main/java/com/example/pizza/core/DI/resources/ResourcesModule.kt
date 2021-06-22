package com.example.pizza.core.DI.resources

import dagger.Binds
import dagger.Module

@Module
abstract class ResourcesModule {
    @Binds
    abstract fun bindResources(resourceProviderImpl: ResourceProviderImpl) : ResourceProvider
}