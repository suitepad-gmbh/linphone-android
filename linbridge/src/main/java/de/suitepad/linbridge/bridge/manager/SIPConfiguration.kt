package de.suitepad.linbridge.bridge.manager

/**
 * Configuration class for the SIP client
 */
class SIPConfiguration {

    /**
     * specify the microphone gain in dB, default is 0
     */
    var microphoneGain: Int = 0

    /**
     * specify the speaker gain in dB, default is 0
     */
    var speakerGain: Int = 0

    /**
     * enable/disable aec, default is false. AEC uses the speaker signal as a reference and tries
     * to suppress it from the  input
     */
    var echoCancellation: Boolean = false

    /**
     * enable/disable echo limiter, if enabled microphone gain will be decreased by [microphoneDecrease] when the speaker
     * output exceeds a certain [speakerThreshold] amount. The microphone gain is then decreased for
     * [echoLimiterSustain]ms
     *
     * echo limiter will be bypassed in case of double talk detection
     */
    var echoLimiter: Boolean = false

    /**
     * linear amount of microphone gain decrease when [echoLimiter] is active
     */
    var microphoneDecrease: Int = 0

    /**
     * linear amount of speaker gain output to be reached to that [echoLimiter] decreases the microphone gain
     */
    var speakerThreshold: Float = 0F

    /**
     * microphone gain decrease sustain when [echoLimiter] is active
     */
    val echoLimiterSustain: Int = 0

}