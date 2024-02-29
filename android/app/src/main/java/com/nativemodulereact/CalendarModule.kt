package com.nativemodulereact

import androidx.annotation.NonNull
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import java.util.*
import android.util.Log
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.Arguments

class CalendarModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private var eventCount = 0

    @NonNull
    override fun getName(): String {
        return "CalendarModule"
    }

    @ReactMethod
    fun createCalendarEvent(callback: Callback) {
        Log.d("Calendar Module", "Logged from our calendar module")
        callback.invoke("Data returned from Native calendar module")
    }

    @ReactMethod
    fun createCalendarPromise(promise: Promise) {
        try {
            promise.resolve("Data returned from promise")
            eventCount++
            sendEvent(reactApplicationContext, "EventCount", eventCount)
        } catch (e: Exception) {
            promise.reject("Error returned from promise", e)
        }
    }

    private fun sendEvent(reactContext: ReactContext, eventName: String, params: Int) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(eventName, params)
    }
}