package com.klid.demo_android_chat_chatcamp

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.klid.demo_android_chat_chatcamp.databinding.FragmentFirstBinding
import io.chatcamp.sdk.ChatCamp
import io.chatcamp.sdk.ChatCampException
import io.chatcamp.sdk.User
import timber.log.Timber

class FirstFragment : Fragment() {

  private var _binding: FragmentFirstBinding? = null

  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentFirstBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.buttonConnect.setOnClickListener {
      val username = binding.username.text?.toString()?.trim()
      if (username.isNullOrBlank()) {
        Timber.w("Username must not be null or blank")
        return@setOnClickListener
      }

      connectChatUser(username)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun getUniqueDeviceId(): String {
    val deviceId = Settings.Secure.getString(requireContext().contentResolver, Settings.Secure.ANDROID_ID)
    Timber.i("deviceId $deviceId")
    return deviceId
  }

  private fun connectChatUser(username: String) {
    val userId = getUniqueDeviceId()

    ChatCamp.connect(userId) { user: User, ex: ChatCampException ->
      Timber.e(ex, "error when connecting user to ChatCamp")
      Timber.i("ChatCamp user data $user")

      updateUserDisplayName(username)
    }
  }

  private fun updateUserDisplayName(username: String) {
    ChatCamp.updateUserDisplayName(username) { user, _ ->
      Timber.i("user display name updated $user")
    }
  }
}
