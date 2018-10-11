package com.fearaujo.data.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NetworkChangeReceiver(private val callback: ConnectionCallback? = null) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val status = NetworkUtil.getConnectivityStatusString(context)
        callback?.onConnectionChange(status)
    }
}