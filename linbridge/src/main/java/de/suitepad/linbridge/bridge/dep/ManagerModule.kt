package de.suitepad.linbridge.bridge.dep

import android.content.Context
import dagger.Module
import dagger.Provides
import de.suitepad.linbridge.bridge.manager.BridgeLinphoneCoreListener
import de.suitepad.linbridge.bridge.manager.IManager
import de.suitepad.linbridge.bridge.manager.LinphoneManager
import org.linphone.core.*
import javax.inject.Named

@Module(includes = [BridgeModule::class])
class ManagerModule(val debug: Boolean) {

    @Provides
    fun linphoneManager(core: Core): IManager {
        return LinphoneManager(core)
    }

    @Provides
    fun linphoneCore(
            linphoneCoreListener: CoreListener,
            factory: Factory,
            context: Context
    ): Core {
        return factory.createCore(null, null, context).apply {
            addListener(linphoneCoreListener)
        }
    }

    @Provides
    fun linphoneCoreListener(): CoreListener {
        return BridgeLinphoneCoreListener()
    }

    @Provides
    fun linphoneCoreFactory(@DebugFlag debug: Boolean): Factory {
        return Factory.instance().apply {
            setDebugMode(debug, "LibLinphone")
        }
    }

    @Provides
    @DebugFlag
    fun provideIsDebug(): Boolean = debug

    @Named("debug")
    annotation class DebugFlag

}