package com.klid.demo_android_chat_chatcamp

import android.app.Application
import io.chatcamp.sdk.ChatCamp
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * @author Ivan Kaptue
 */
class App : Application() {

  companion object {
    @JvmStatic
    val APP_ID = ""
  }

  override fun onCreate() {
    super.onCreate()
    initTimber()
    initChatCamp()
  }

  private fun initChatCamp() {
    Timber.i("Init ChatCamp")
    ChatCamp.init(this, APP_ID)
  }

  private fun initTimber() {
    if (BuildConfig.DEBUG) Timber.plant(DebugTree())
  }
}
