package com.leatien.lepaikt

import android.app.Activity
import android.app.Application
import com.leatien.lepaikt.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

/**
 * Created by wuhaowen on 2018/3/22.
 */
class MKAplication : Application() , HasActivityInjector {
    companion object {
        lateinit var instance: MKAplication
            private set
    }

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        instance = this
//        Realm.init(instance)
//        val config = RealmConfiguration.Builder()
//                .deleteRealmIfMigrationNeeded()
//                .build()
//        Realm.setDefaultConfiguration(config)

        DaggerAppComponent.builder()
                .application(this)
                .build().inject(this)
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}