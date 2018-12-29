package com.leatien.lepaikt.di.components

import android.app.Application
import com.leatien.lepaikt.MKAplication
import com.leatien.lepaikt.business.activity.InitActivityBuilder
import com.leatien.lepaikt.di.ApiModule
import com.leatien.lepaikt.di.ApplicationModule
import com.leatien.lepaikt.di.RetrofitModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by wuhaowen on 2018/3/22.
 */
@Component(modules = [
    (AndroidInjectionModule::class),
    (ApplicationModule::class),
    (RetrofitModule::class),
    (ApiModule::class),
    (InitActivityBuilder::class)
])
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MKAplication)
}