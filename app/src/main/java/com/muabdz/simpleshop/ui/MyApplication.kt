package com.muabdz.simpleshop.ui

import android.app.Application
import com.muabdz.simpleshop.di.AppComponent
import com.muabdz.simpleshop.di.DaggerAppComponent

class MyApplication: Application() {

    val appComponent: AppComponent = DaggerAppComponent.create()
}
