package com.android.component.androidcomponents

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.android.component.accounts_ui_component.BasePhoneVerificationActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PhoneVerificationActivity : BasePhoneVerificationActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setLayout()
  }

  private fun setLayout() {
    appLogo.setImageResource(R.mipmap.ic_launcher)
    titleLabel.text = "Enter Verification Code"
    subTitleLabel.text =
      "We sent a code to 09XX-XXXX-XXXX. Enter the code below to verify the number."
    codeInput.hint = "6-Digit Code"
    loginButton.apply {
      text = "Login"
      setOnClickListener {
        lifecycleScope.launch {
          loading(true, DEFAULT_LOGIN_TEXT)
          delay(2000L)
          finish()
        }
      }
    }
  }

  companion object {
    private const val DEFAULT_LOGIN_TEXT = "Login"
  }
}
