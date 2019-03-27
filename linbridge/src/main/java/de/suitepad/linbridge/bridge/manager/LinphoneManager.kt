package de.suitepad.linbridge.bridge.manager

import de.suitepad.linbridge.bridge.dep.BridgeServiceComponent
import de.suitepad.linbridge.bridge.manager.dep.DaggerLinphoneManagerComponent
import de.suitepad.linbridge.bridge.manager.dep.LinphoneManagerComponent
import de.suitepad.linbridge.bridge.manager.dep.LinphoneManagerModule
import org.linphone.core.Core
import javax.inject.Inject

class LinphoneManager(bridgeServiceComponent: BridgeServiceComponent) {

    val component: LinphoneManagerComponent = DaggerLinphoneManagerComponent.builder()
        .bridgeServiceComponent(bridgeServiceComponent)
        .linphoneManagerModule(LinphoneManagerModule(true))
        .build()

    @Inject
    lateinit var core: Core

    init {
        component.inject(this)
    }

}