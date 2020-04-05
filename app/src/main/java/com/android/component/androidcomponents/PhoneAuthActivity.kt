package com.android.component.androidcomponents

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.android.component.accounts_ui_component.BasePhoneAuthActivity
import kotlinx.coroutines.launch

class PhoneAuthActivity : BasePhoneAuthActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setLayout()

    setTransition()
  }

  private fun setLayout() {
    appLogo.setImageResource(R.mipmap.ic_launcher)
    numberLabel.text = LABEL
    numberInput.hint = HINT
    loginButton.apply {
      text = DEFAULT_LOGIN_TEXT
      setOnClickListener {
        lifecycleScope.launch {
          loginToLanding()
          navigateToVerification()
        }
      }
    }
  }

  private fun setTransition() {
    lifecycleScope.launchWhenCreated {
      // load landing
      loadLanding()
      // load login
      landingToLogin()
    }
  }

  private fun navigateToVerification() {
    Intent(this, PhoneVerificationActivity::class.java).apply {
      startActivity(this)
    }
  }

  companion object {
    private const val LABEL = "Mobile Number"
    private const val HINT = "09X-XXX-XXXX"
    private const val DEFAULT_LOGIN_TEXT = "Sign ip / Log in"
    private const val LANDING_DURATION = 2000L
    private const val LANDING_TO_LOGIN_DURATION = 1500L
  }
}
