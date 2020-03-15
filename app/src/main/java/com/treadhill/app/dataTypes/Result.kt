package com.treadhill.app.dataTypes

class Result<out V>(val value: V?, val exception: Exception?) {
    constructor(value: V?) : this(value, null)
    constructor(error: Exception?) : this(null, error)
}