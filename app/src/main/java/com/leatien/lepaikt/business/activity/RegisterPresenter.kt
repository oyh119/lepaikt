package com.leatien.lepaikt.business.activity

import com.leatien.lepaikt.apis.InitService
import com.leatien.lepaikt.common.StoreHolder
import javax.inject.Inject

/**
 * Created by wuhaowen on 2018/3/22.
 */
class RegisterPresenter @Inject constructor(val service: InitService, val view: RegisterContract.View, private val storeHolder: StoreHolder) : RegisterContract.Presenter{

//    override fun login(storeCode: String, pwd: String,error:(Error) -> Unit, success: () -> Unit) {
//        service.activate(storeCode,pwd, deviceUid())
//                .doOnNext {
//                    it.apply{
//                        storeHolder.sessionId = sessionId
//                        storeHolder.storeId = storeId
//                        storeHolder.storeName = storeName
//                        storeHolder.tenantId = tenantId
//                        storeHolder.tenantName = tenantName
//                        storeHolder.typeId = typeId
//                    }
//                }
//                .asyncUntil(view.bindUntilEvent(ActivityEvent.DESTROY))
//                .subscribe({
//                    success()
//                },{
//                    error(it)
//                })
//    }
}