package com.treadhill.app.factory_and_ingection

import android.content.Context

object InjectionUtils {

    fun provideMainViewModelFactory(context: Context): MainViewModelFactory =
        MainViewModelFactory(context)
}