package de.suitepad.linbridge.bridge.manager

import org.linphone.core.*
import java.nio.ByteBuffer

class BridgeLinphoneCoreListener: CoreListener {
    override fun onTransferStateChanged(lc: Core?, transfered: Call?, newCallState: Call.State?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFriendListCreated(lc: Core?, list: FriendList?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSubscriptionStateChanged(lc: Core?, lev: Event?, state: SubscriptionState?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCallLogUpdated(lc: Core?, newcl: CallLog?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCallStateChanged(lc: Core?, call: Call?, cstate: Call.State?, message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAuthenticationRequested(lc: Core?, authInfo: AuthInfo?, method: AuthMethod?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNotifyPresenceReceivedForUriOrTel(lc: Core?, lf: Friend?, uriOrTel: String?, presenceModel: PresenceModel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChatRoomStateChanged(lc: Core?, cr: ChatRoom?, state: ChatRoom.State?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBuddyInfoUpdated(lc: Core?, lf: Friend?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNetworkReachable(lc: Core?, reachable: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNotifyReceived(lc: Core?, lev: Event?, notifiedEvent: String?, body: Content?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNewSubscriptionRequested(lc: Core?, lf: Friend?, url: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRegistrationStateChanged(lc: Core?, cfg: ProxyConfig?, cstate: RegistrationState?, message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNotifyPresenceReceived(lc: Core?, lf: Friend?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEcCalibrationAudioInit(lc: Core?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMessageReceived(lc: Core?, room: ChatRoom?, message: ChatMessage?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEcCalibrationResult(lc: Core?, status: EcCalibratorStatus?, delayMs: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSubscribeReceived(lc: Core?, lev: Event?, subscribeEvent: String?, body: Content?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onInfoReceived(lc: Core?, call: Call?, msg: InfoMessage?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCallStatsUpdated(lc: Core?, call: Call?, stats: CallStats?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFriendListRemoved(lc: Core?, list: FriendList?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onReferReceived(lc: Core?, referTo: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQrcodeFound(lc: Core?, result: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConfiguringStatus(lc: Core?, status: ConfiguringState?, message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCallCreated(lc: Core?, call: Call?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPublishStateChanged(lc: Core?, lev: Event?, state: PublishState?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCallEncryptionChanged(lc: Core?, call: Call?, on: Boolean, authenticationToken: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onIsComposingReceived(lc: Core?, room: ChatRoom?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMessageReceivedUnableDecrypt(lc: Core?, room: ChatRoom?, message: ChatMessage?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLogCollectionUploadProgressIndication(lc: Core?, offset: Int, total: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onVersionUpdateCheckResultReceived(lc: Core?, result: VersionUpdateCheckResult?, version: String?, url: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onEcCalibrationAudioUninit(lc: Core?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGlobalStateChanged(lc: Core?, gstate: GlobalState?, message: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLogCollectionUploadStateChanged(lc: Core?, state: Core.LogCollectionUploadState?, info: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDtmfReceived(lc: Core?, call: Call?, dtmf: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}