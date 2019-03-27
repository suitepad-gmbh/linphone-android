package de.suitepad.linbridge.bridge

import android.app.Service
import android.content.Intent
import android.os.IBinder
import de.suitepad.linbridge.app.BridgeApplication
import de.suitepad.linbridge.bridge.dep.BridgeModule
import de.suitepad.linbridge.bridge.dep.BridgeServiceComponent
import de.suitepad.linbridge.bridge.dep.DaggerBridgeServiceComponent
import de.suitepad.linbridge.bridge.manager.IManager
import javax.inject.Inject

class BridgeService : Service() {

    companion object {
        //        const val PLACEHOLDER = "de.suitepad.linbridge.bridge.BridgeService.PLACEHOLDER"

        const val ACTION_START_SERVICE = "de.suitepad.linbridge.bridge.BridgeService.ACTION_START_SERVICE"
        const val ACTION_STOP_SERVICE = "de.suitepad.linbridge.bridge.BridgeService.ACTION_STOP_SERVICE"

        const val EXTRA_SIP_SERVER = "de.suitepad.linbridge.bridge.BridgeService.EXTRA_SIP_SERVER"
        const val EXTRA_SIP_USERNAME = "de.suitepad.linbridge.bridge.BridgeService.EXTRA_SIP_USERNAME"
        const val EXTRA_SIP_PASSWORD = "de.suitepad.linbridge.bridge.BridgeService.EXTRA_SIP_PASSWORD"
        const val EXTRA_SIP_PORT = "de.suitepad.linbridge.bridge.BridgeService.EXTRA_SIP_PASSWORD"
        const val EXTRA_SIP_PROXY = "de.suitepad.linbridge.bridge.BridgeService.EXTRA_SIP_PROXY"

    }

    lateinit var component: BridgeServiceComponent

    @Inject
    lateinit var linphoneManager: IManager

    override fun onCreate() {
        super.onCreate()
        component = DaggerBridgeServiceComponent.builder()
            .appComponent(BridgeApplication.getApplication(this).component)
            .bridgeModule(BridgeModule(this))
            .build()

        component.inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var result = super.onStartCommand(intent, flags, startId)
        intent?.let {
            when (it.action) {
                ACTION_START_SERVICE -> {
                    val sipServer = intent.getStringExtra(EXTRA_SIP_SERVER)
                    val sipUsername = intent.getStringExtra(EXTRA_SIP_USERNAME)
                    val sipPassword = intent.getStringExtra(EXTRA_SIP_PASSWORD)
                    val sipPort = intent.getStringExtra(EXTRA_SIP_PORT)
                    val sipProxy = intent.getStringExtra(EXTRA_SIP_PROXY)
                }
                ACTION_STOP_SERVICE -> {
                    stopSelf()
                }
            }
        }
        return result
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

}
