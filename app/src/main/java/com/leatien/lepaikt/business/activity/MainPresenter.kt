package com.leatien.lepaikt.business.activity

import com.leatien.lepaikt.apis.InitService
import com.leatien.lepaikt.common.StoreHolder
import com.leatien.lepaikt.common.asyncUntil
import com.leatien.lepaikt.common.deviceUid
import com.trello.rxlifecycle2.android.ActivityEvent
import javax.inject.Inject

/**
 * Created by wuhaowen on 2018/3/22.
 */
class MainPresenter @Inject constructor(val service: InitService, val view: MainContract.View, private val storeHolder: StoreHolder) : MainContract.Presenter{

    override fun login(storeCode: String, pwd: String,error:(Error) -> Unit, success: () -> Unit) {
        service.activate(storeCode,pwd, deviceUid())
                .doOnNext {
                    it.apply{
                        storeHolder.sessionId = sessionId
                        storeHolder.storeId = storeId
                        storeHolder.storeName = storeName
                        storeHolder.tenantId = tenantId
                        storeHolder.tenantName = tenantName
                        storeHolder.typeId = typeId
                    }
                }
                .asyncUntil(view.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe({
                    success()
                },{
                    error(it)
                })
    }
}