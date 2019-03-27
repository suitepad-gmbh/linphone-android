package de.suitepad.linbridge.bridge.dep

import android.content.Context
import dagger.Module
import dagger.Provides
import de.suitepad.linbridge.bridge.BridgeService
import de.suitepad.linbridge.bridge.manager.LinphoneManager

@Module
class BridgeModule(val context: BridgeService) {

    @Provides
    fun linphoneManager(bridgeComponent: BridgeServiceComponent): LinphoneManager {
        return LinphoneManager(bridgeComponent)
    }

    @Provides
    fun context(): Context = context

}