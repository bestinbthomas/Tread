package com.treadhill.app.dataTypes

/**
 * wrapper class for response from firebase
 *
 * @param V Type of response
 * @property value response value. null if request failed
 * @property exception exception thrown bu the request. null if request was successful
 */
class Result<out V>(val value: V?, val exception: Exception?) {
    constructor(value: V?) : this(value, null)
    constructor(error: Exception?) : this(null, error)
}