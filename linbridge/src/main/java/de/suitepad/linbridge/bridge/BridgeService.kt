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
import de.suitepad.linbridge.bridge.manager.configuration.Codec
import de.suitepad.linbridge.bridge.manager.configuration.SIPConfiguration
import timber.log.Timber
import javax.inject.Inject

const val MESSAGE_BIND = 1
const val MESSAGE_AUTHENTICATE = 2
const val MESSAGE_CONFIGURE = 3
const val MESSAGE_CALL = 4
const val MESSAGE_END_CALL = 5

const val ACTION_START_SERVICE = "de.suitepad.linbridge.bridge.BridgeService.ACTION_START_SERVICE"
const val ACTION_STOP_SERVICE = "de.suitepad.linbridge.bridge.BridgeService.ACTION_STOP_SERVICE"

const val EXTRA_SIP_SERVER = "EXTRA_SIP_SERVER"
const val EXTRA_SIP_USERNAME = "EXTRA_SIP_USERNAME"
const val EXTRA_SIP_PASSWORD = "EXTRA_SIP_PASSWORD"
const val EXTRA_SIP_PORT = "EXTRA_SIP_PASSWORD"
const val EXTRA_SIP_PROXY = "EXTRA_SIP_PROXY"

const val EXTRA_MICROPHONE_GAIN         = "EXTRA_MICROPHONE_GAIN"
const val EXTRA_SPEAKER_GAIN            = "EXTRA_SPEAKER_GAIN"
const val EXTRA_AEC_ENABLED             = "EXTRA_AEC_ENABLED"
const val EXTRA_EL_ENABLED              = "EXTRA_EL_ENABLED"
const val EXTRA_EL_MIC_REDUCTION        = "EXTRA_EL_MICROPHONE_REDUCTION"
const val EXTRA_EL_SPEAKER_THRESHOLD    = "EXTRA_EL_SPEAKER_THRESHOLD"
const val EXTRA_EL_SUSTAIN              = "EXTRA_EL_SUSTAIN"
const val EXTRA_EL_DOUBLETALK_THRESHOLD = "EXTRA_EL_DOUBLETALK_THRESHOLD"
const val EXTRA_LIST_CODEC_ENABLED      = "EXTRA_LIST_CODEC_ENABLED"

class BridgeService : Service() {

    lateinit var component: BridgeServiceComponent

    @Inject
    lateinit var linphoneManager: IManager

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

    fun authenticate(bundle: Bundle) {
        val sipServer = bundle.getString(EXTRA_SIP_SERVER)
        val sipUsername = bundle.getString(EXTRA_SIP_USERNAME)
        val sipPassword = bundle.getString(EXTRA_SIP_PASSWORD)
        val sipPort = bundle.getInt(EXTRA_SIP_PORT, 5060)
        val sipProxy = bundle.getString(EXTRA_SIP_PROXY)

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

    fun startService() {
        linphoneManager.start()
    }

    fun stopService() {
        linphoneManager.destroy()
        stopSelf()
    }

    fun configure(bundle: Bundle) {
        SIPConfiguration().apply {
            microphoneGain = bundle.getInt(EXTRA_MICROPHONE_GAIN)
            speakerGain = bundle.getInt(EXTRA_SPEAKER_GAIN)
            echoCancellation = bundle.getBoolean(EXTRA_AEC_ENABLED)
            echoLimiter = bundle.getBoolean(EXTRA_EL_ENABLED)
            microphoneDecrease = bundle.getInt(EXTRA_EL_MIC_REDUCTION)
            speakerThreshold =  bundle.getFloat(EXTRA_EL_SPEAKER_THRESHOLD)
            doubleTalkDetection = bundle.getFloat(EXTRA_EL_DOUBLETALK_THRESHOLD)
            echoLimiterSustain = bundle.getInt(EXTRA_EL_SUSTAIN)
            enabledCodecs = bundle.getStringArray(EXTRA_LIST_CODEC_ENABLED)?.mapNotNull {
                try {
                    Codec.valueOf(it)
                } catch (ex: IllegalArgumentException) {
                    null
                }
            }
        }
    }

    fun registerCallback() {

    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

}
