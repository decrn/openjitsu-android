package com.openjitsu.android.openjitsu

import android.app.Application
import com.openjitsu.android.openjitsu.di.AppComponent


class Application: Application() {
    companion object {
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this,"https://gist.githubusercontent.com/decrn/9333d4c9fc9bab3084964d053b708561/raw/")
    }


}