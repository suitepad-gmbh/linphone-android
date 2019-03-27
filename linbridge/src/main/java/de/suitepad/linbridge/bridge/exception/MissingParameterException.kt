package de.suitepad.linbridge.bridge.exception

import java.lang.Exception

class MissingParameterException(parameterName: String): Exception("parameter $parameterName missing")