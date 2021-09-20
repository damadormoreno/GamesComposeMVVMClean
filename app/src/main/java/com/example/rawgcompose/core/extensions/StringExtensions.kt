package com.example.rawgcompose.core.extensions

fun String?.shouldPerformSearch(): Boolean {
    return this.isNullOrBlank().not() && this!!.length >= 3
}