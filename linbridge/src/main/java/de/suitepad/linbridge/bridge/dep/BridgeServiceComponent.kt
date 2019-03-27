package de.suitepad.linbridge.bridge.dep

import android.content.Context
import dagger.Component
import de.suitepad.linbridge.app.dep.AppComponent
import de.suitepad.linbridge.bridge.BridgeService
import de.suitepad.linbridge.bridge.manager.LinphoneManager
import javax.inject.Scope

@Component(dependencies = [AppComponent::class], modules = [BridgeModule::class])
@BridgeServiceComponent.LinphoneBridgeServiceScope
interface BridgeServiceComponent {

    fun provideLinphoneManager(): LinphoneManager

    fun provideServiceContext(): Context

    fun inject(service: BridgeService)

    @Scope
    annotation class LinphoneBridgeServiceScope

}