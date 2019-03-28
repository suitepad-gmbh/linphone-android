package de.suitepad.linbridge.bridge.manager

import de.suitepad.linbridge.api.SIPConfiguration
import org.linphone.core.Core
import java.util.*

class LinphoneManager(val core: Core): IManager {

    val keepAliveTask = object : TimerTask() {
        override fun run() {
            core.iterate()
        }
    }
    val keepAliveTimer = Timer("Linphone scheduler")

    override fun start() {
        keepAliveTimer.schedule(keepAliveTask, 0, 20)
    }

    override fun destroy() {
        keepAliveTimer.cancel()
    }

    override fun configure(settings: SIPConfiguration?) {
    }

    override fun authenticate(host: String, port: Int, username: String, password: String, proxy: String?) {
    }

}