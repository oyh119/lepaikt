package com.leatien.lepaikt.business.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by oyh on 2018/12/13
 */
@Module
abstract class InitActivityBuilder {
    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(RegisterModule::class)])
    abstract fun contributeRegisterActivity():RegisterActivity

    @ContributesAndroidInjector(modules = [(HomePageModule::class)])
    abstract fun contributeHomePageActivity() : HomePageActivity

    @ContributesAndroidInjector(modules = [(ProductIsAuctionDetailModule::class)])
    abstract fun contributeProductIsAuctionDetailActivity() : ProductIsAuctionDetailActivity

    @ContributesAndroidInjector(modules = [(OrderMainModule::class)])
    abstract fun contributeOrderMainActivity() : OrderMainActivity

}