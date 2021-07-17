package com.muabdz.simpleshop.di

import com.muabdz.simpleshop.data.source.HomeRepository
import com.muabdz.simpleshop.ui.home.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    fun repository(): HomeRepository
    fun inject(activity: HomeActivity)
}