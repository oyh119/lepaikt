package com.leatien.lepaikt.business.activity

import dagger.Module
import dagger.Provides

/**
 * Created by oyh on 2018/12/20
 */
@Module
class HomePageModule {

    @Provides
    fun provideHomePageView(homePageActivity: HomePageActivity): HomePageContract.View = homePageActivity

    @Provides
    fun provideHomePagePresenter(homePagePresenter: HomePagePresenter): HomePageContract.Presenter = homePagePresenter
}