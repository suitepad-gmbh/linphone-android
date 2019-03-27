package de.suitepad.linbridge.bridge

import android.os.Bundle
import android.os.Messenger

interface IBridgeService {

    /**
     * authenticate and connect to the SIP  server
     */
    fun authenticate(payload: Bundle)

    /**
     * configure SIP server
     */
    fun configure(payload: Bundle)

    /**
     * register a callback messenger
     */
    fun registerCallback(callback: Messenger)

}