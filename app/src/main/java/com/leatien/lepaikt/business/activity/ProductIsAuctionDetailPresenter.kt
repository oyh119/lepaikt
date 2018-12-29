package com.leatien.lepaikt.business.activity

import com.leatien.lepaikt.apis.InitService
import com.leatien.lepaikt.common.StoreHolder
import javax.inject.Inject

/**
 * Created by oyh on 2018/12/29
 */
class ProductIsAuctionDetailPresenter @Inject constructor(val service: InitService, val view: ProductIsAuctionDetailContract.View, private val storeHolder: StoreHolder) : ProductIsAuctionDetailContract.Presenter{

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