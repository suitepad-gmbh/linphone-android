package de.suitepad.linbridge.bridge

import android.os.Handler
import android.os.Message
import org.json.JSONObject


const val MESSAGE_BIND = 1
const val MESSAGE_AUTHENTICATE = 2
const val MESSAGE_CONFIGURE = 3
const val MESSAGE_CALL = 4
const val MESSAGE_END_CALL = 5

internal class MessengerHandler(val bridgeService: IBridgeService) : Handler() {

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            MESSAGE_BIND -> {
                if (msg.replyTo != null) {
                    bridgeService.registerCallback(msg.replyTo)
                }
            }
            MESSAGE_AUTHENTICATE -> {
                bridgeService.authenticate(msg.data)
            }
            MESSAGE_CONFIGURE -> {
                bridgeService.configure(msg.data)
            }
            MESSAGE_CALL -> {

            }
            MESSAGE_END_CALL -> {

            }
            else -> super.handleMessage(msg)
        }
    }

}
