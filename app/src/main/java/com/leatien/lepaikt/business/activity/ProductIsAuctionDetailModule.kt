package com.leatien.lepaikt.business.activity

import dagger.Module
import dagger.Provides

/**
 * Created by oyh on 2018/12/20
 */
@Module
class ProductIsAuctionDetailModule {

    @Provides
    fun provideProductIsAuctionView(productIsAuctionDetailActivity: ProductIsAuctionDetailActivity): ProductIsAuctionDetailContract.View = productIsAuctionDetailActivity

    @Provides
    fun provideProductIsAuctionPresenter(productIsAuctionDetailPresenter: ProductIsAuctionDetailPresenter): ProductIsAuctionDetailContract.Presenter = productIsAuctionDetailPresenter
}