package com.leatien.lepaikt.business.activity

import dagger.Module
import dagger.Provides

/**
 * Created by oyh on 2018/12/20
 */
@Module
class OrderMainModule {

    @Provides
    fun provideOrderMainView(orderMainActivity: OrderMainActivity): ProductIsAuctionDetailContract.View = orderMainActivity

    @Provides
    fun provideOrderMainPresenter(orderMainPresenter: OrderMainPresenter): ProductIsAuctionDetailContract.Presenter = orderMainPresenter
}