package com.leatien.lepaikt.business.activity

import dagger.Module
import dagger.Provides

/**
 * Created by wuhaowen on 2018/3/22.
 */
@Module
class RegisterModule {

    @Provides
    fun provideRegisterView(registerActivity: RegisterActivity): RegisterContract.View = registerActivity

    @Provides
    fun provideRegisterPresenter(registerPresenter: RegisterPresenter): RegisterContract.Presenter = registerPresenter
}