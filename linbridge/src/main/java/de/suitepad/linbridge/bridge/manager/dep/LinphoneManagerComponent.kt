package de.suitepad.linbridge.bridge.manager.dep

import dagger.Component
import de.suitepad.linbridge.bridge.dep.BridgeServiceComponent
import de.suitepad.linbridge.bridge.manager.LinphoneManager
import javax.inject.Scope

@Component(dependencies = [BridgeServiceComponent::class], modules = [LinphoneManagerModule::class])
@LinphoneManagerComponent.LinphoneManagerScope
interface LinphoneManagerComponent {

    fun inject(linphoneManager: LinphoneManager)

    @Scope
    annotation class LinphoneManagerScope

}