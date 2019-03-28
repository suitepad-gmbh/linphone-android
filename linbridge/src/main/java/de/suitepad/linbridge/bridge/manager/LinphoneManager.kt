package de.suitepad.linbridge.bridge.manager

import de.suitepad.linbridge.api.SIPConfiguration
import org.linphone.core.Address
import org.linphone.core.Core
import org.linphone.core.Factory
import java.util.*

class LinphoneManager(val core: Core, val coreFactory: Factory) : IManager {

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
        core.clearAllAuthInfo()

        val sipAddress = "<sip:$username@$host:$port>"
        val address: Address = coreFactory.createAddress(sipAddress)

        val authenticationInfo = coreFactory.createAuthInfo(address.username, address.username,
                password, null, null, address.domain)

        val proxyConfig = core.createProxyConfig()
        proxyConfig.identityAddress = address
        var sipProxy = "sip:"
        if (proxy == null) {
            sipProxy += proxy
        } else {
            if (!proxy.startsWith("sip:") && !proxy.startsWith("<sip:") &&
                    !proxy.startsWith("sips:") && !proxy.startsWith("<sips:")) {
                sipProxy += proxy
            } else {
                sipProxy = proxy
            }
        }
        val proxyAddress: Address = coreFactory.createAddress(sipProxy)
        proxyConfig.enableRegister(true)
        proxyConfig.serverAddr = proxyAddress.asStringUriOnly()
        // todo: check if proxy is outbound only in which case would set route to proxyAddress.asStringriOnly

        core.addProxyConfig(proxyConfig)
        core.addAuthInfo(authenticationInfo)
        core.defaultProxyConfig = proxyConfig
        core.refreshRegisters()
    }

}