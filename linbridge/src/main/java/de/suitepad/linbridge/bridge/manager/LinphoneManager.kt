package de.suitepad.linbridge.bridge.manager

import de.suitepad.linbridge.bridge.manager.configuration.SIPConfiguration
import org.linphone.core.Core

class LinphoneManager(val core: Core): IManager {

    override fun start() {
    }

    override fun destroy() {
    }

    override fun configure(settings: SIPConfiguration) {
    }

    override fun authenticate(host: String, port: Int, username: String, password: String, proxy: String?) {
    }

}