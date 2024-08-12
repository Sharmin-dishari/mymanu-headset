/*
 * ************************************************************************************************
 * * © 2022 Qualcomm Technologies, Inc. and/or its subsidiaries. All rights reserved.             *
 * ************************************************************************************************
 */
package com.mymanu.companionapp.core.requests.qtil

import android.content.Context
import com.mymanu.companionapp.core.GaiaClientService
import com.mymanu.companionapp.core.gaia.qtil.data.XpanTransport
import com.mymanu.companionapp.core.gaia.qtil.plugins.XpanPlugin
import com.mymanu.companionapp.core.requests.core.Request

sealed class XpanRequest(val execute: XpanPlugin.() -> Unit) : Request<Void, Void, Void>(null) {

    class ConnectAP : XpanRequest({ this.connectAP() })
    class DisconnectAP : XpanRequest({ this.disconnectAP() })
    class ToggleTransport : XpanRequest({ this.toggleTransport() })
    class SetTransport(val transport: XpanTransport) : XpanRequest({ this.setTransport(transport) })

    override fun run(context: Context?) {
        val plugin = GaiaClientService.getQtilManager().xpanPlugin
        if (plugin != null) {
            plugin.execute()
            onComplete(null)
        } else {
            onError(null)
        }
    }

    override fun onEnd() {}
}
