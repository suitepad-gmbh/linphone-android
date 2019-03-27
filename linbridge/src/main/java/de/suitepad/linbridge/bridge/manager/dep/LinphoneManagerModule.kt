package de.suitepad.linbridge.bridge.manager.dep

import android.content.Context
import dagger.Module
import dagger.Provides
import de.suitepad.linbridge.bridge.manager.BridgeLinphoneCoreListener
import org.linphone.core.*
import javax.inject.Named

@Module
class LinphoneManagerModule(val debug: Boolean) {

    @Provides
    fun linphoneCore(
            linphoneCoreListener: CoreListener,
            factory: Factory,
            context: Context
    ): Core {
        return factory.createCore(null, null, context).apply {
            ring = "/toy_mono.wav"
            setRingback("/ringback.wav")
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