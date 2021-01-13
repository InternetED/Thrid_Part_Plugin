package com.pikolive.module.ad

import com.pikolive.module.ad.enums.AdProvider
import com.pikolive.module.ad.factory.AbstractAbFactory
import com.pikolive.module.ad.factory.FacebookAdFactory
import com.pikolive.module.ad.factory.GoogleAdFactory
import com.pikolive.module.ad.factory.MediationAbFactory


/**
 * Creator: ED
 * Date: 2020/5/26 9:47 AM
 * Mail: salahayo3192@gmail.com
 *
 * **/
class AdManager {

    private val factoryMap = mutableMapOf<AdProvider, AbstractAbFactory>()


    /**
     * 取得廣告供應商工廠
     *
     * @param adProvider 廣告供應商
     * */
    fun with(adProvider: AdProvider): AbstractAbFactory {
        return findFactory(adProvider)
    }


    /**
     * 負責提供廣告的產出工廠
     * */
    private fun findFactory(type: AdProvider): AbstractAbFactory {
        if (factoryMap.containsKey(type)) {
            return factoryMap.getValue(type)
        }

        return when (type) {
            AdProvider.Google -> GoogleAdFactory()
            AdProvider.Facebook -> FacebookAdFactory()
            AdProvider.Mediation -> MediationAbFactory()
        }.apply {
            factoryMap[type] = this
        }
    }


    companion object {
        private val INSTANCE: AdManager by lazy {
            AdManager()
        }

        fun getInstance() = INSTANCE
    }
}