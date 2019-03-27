package de.suitepad.linbridge.bridge

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.*
import de.suitepad.linbridge.app.BridgeApplication
import de.suitepad.linbridge.bridge.dep.BridgeModule
import de.suitepad.linbridge.bridge.dep.BridgeServiceComponent
import de.suitepad.linbridge.bridge.dep.DaggerBridgeServiceComponent
import de.suitepad.linbridge.bridge.dep.ManagerModule
import de.suitepad.linbridge.bridge.exception.MissingParameterException
import de.suitepad.linbridge.bridge.manager.IManager
import de.suitepad.linbridge.bridge.manager.configuration.SIPConfiguration
import timber.log.Timber
import javax.inject.Inject

const val ACTION_START_SERVICE = "de.suitepad.linbridge.bridge.BridgeService.ACTION_START_SERVICE"
const val ACTION_STOP_SERVICE = "de.suitepad.linbridge.bridge.BridgeService.ACTION_STOP_SERVICE"

const val EXTRA_SIP_SERVER = "EXTRA_SIP_SERVER"
const val EXTRA_SIP_USERNAME = "EXTRA_SIP_USERNAME"
const val EXTRA_SIP_PASSWORD = "EXTRA_SIP_PASSWORD"
const val EXTRA_SIP_PORT = "EXTRA_SIP_PASSWORD"
const val EXTRA_SIP_PROXY = "EXTRA_SIP_PROXY"

const val EXTRA_MICROPHONE_GAIN = "EXTRA_MICROPHONE_GAIN"
const val EXTRA_SPEAKER_GAIN = "EXTRA_SPEAKER_GAIN"
const val EXTRA_AEC_ENABLED = "EXTRA_AEC_ENABLED"
const val EXTRA_EL_ENABLED = "EXTRA_EL_ENABLED"
const val EXTRA_EL_MIC_REDUCTION = "EXTRA_EL_MICROPHONE_REDUCTION"
const val EXTRA_EL_SPEAKER_THRESHOLD = "EXTRA_EL_SPEAKER_THRESHOLD"
const val EXTRA_EL_SUSTAIN = "EXTRA_EL_SUSTAIN"
const val EXTRA_EL_DOUBLETALK_THRESHOLD = "EXTRA_EL_DOUBLETALK_THRESHOLD"
const val EXTRA_LIST_CODEC_ENABLED = "EXTRA_LIST_CODEC_ENABLED"

class BridgeService : Service(), IBridgeService {

    lateinit var component: BridgeServiceComponent

    @Inject
    lateinit var linphoneManager: IManager

    var messengerHandler: Messenger? = null
    var callbackMessenger: Messenger? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerBridgeServiceComponent.builder()
                .appComponent(BridgeApplication.getApplication(this).component)
                .bridgeModule(BridgeModule(this))
                .managerModule(ManagerModule(true))
                .build()

        component.inject(this)

        linphoneManager.start()
        startForeground(1, Notification())
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var result = super.onStartCommand(intent, flags, startId)
        intent?.let {
            when (it.action) {
                ACTION_START_SERVICE -> {
                    startService()
                }
                ACTION_STOP_SERVICE -> {
                    Timber.d("stopping service ${Integer.toHexString(hashCode())}")
                    stopService()
                }
                else -> {
                    // do nothing
                }
            }
        }
        return result
    }

    override fun authenticate(payload: Bundle) {
        val sipServer = payload.getString(EXTRA_SIP_SERVER)
        val sipUsername = payload.getString(EXTRA_SIP_USERNAME)
        val sipPassword = payload.getString(EXTRA_SIP_PASSWORD)
        val sipPort = payload.getInt(EXTRA_SIP_PORT)
        val sipProxy = payload.getString(EXTRA_SIP_PROXY)

        if (sipServer == null) {
            throw MissingParameterException(EXTRA_SIP_SERVER)
        }

        if (sipUsername == null) {
            throw MissingParameterException(EXTRA_SIP_USERNAME)
        }

        if (sipPassword == null) {
            throw MissingParameterException(EXTRA_SIP_PASSWORD)
        }

        linphoneManager.authenticate(sipServer, sipPort, sipUsername, sipPassword, sipProxy)
    }

    override fun configure(payload: Bundle) {
        linphoneManager.configure(SIPConfiguration().apply {
            microphoneGain = payload.getInt(EXTRA_MICROPHONE_GAIN)
            speakerGain = payload.getInt(EXTRA_SPEAKER_GAIN)
            echoCancellation = payload.getBoolean(EXTRA_AEC_ENABLED)
            echoLimiter = payload.getBoolean(EXTRA_EL_ENABLED)
            microphoneDecrease = payload.getInt(EXTRA_EL_MIC_REDUCTION)
            speakerThreshold = payload.getFloat(EXTRA_EL_SPEAKER_THRESHOLD)
            doubleTalkDetection = payload.getFloat(EXTRA_EL_DOUBLETALK_THRESHOLD)
            echoLimiterSustain = payload.getInt(EXTRA_EL_SUSTAIN)
            enabledCodecs = payload.getStringArray(EXTRA_LIST_CODEC_ENABLED)?.toList()
        })
    }

    fun startService() {
        linphoneManager.start()
    }

    fun stopService() {
        linphoneManager.destroy()
        stopSelf()
    }

    override fun registerCallback(callback: Messenger) {
        if (callbackMessenger != null) {
            TODO("handle this case and throw an error")
        }
        callbackMessenger = callback
    }

    override fun onBind(intent: Intent): IBinder {
        if (messengerHandler == null) {
            messengerHandler = Messenger(MessengerHandler(this))
        }
        return messengerHandler!!.binder
    }

}
