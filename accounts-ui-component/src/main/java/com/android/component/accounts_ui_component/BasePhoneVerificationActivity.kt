package com.android.component.accounts_ui_component

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_base_phone_verification.*

open class BasePhoneVerificationActivity : AppCompatActivity() {

  val backgroundLayout by lazy { constraintLayout }
  val appLogo by lazy { imageAppLogo }
  val titleLabel by lazy { labelTitle }
  val subTitleLabel by lazy { labelSubTitle }
  val codeInputLayout by lazy { inputLayoutCode }
  val codeInput by lazy { inputCode }
  val loginButton by lazy { buttonLogin }
  val loginProgressBar by lazy { progressBarLogin }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base_phone_verification)
  }

  fun loading(boolean: Boolean, buttonText: String) {
    if (boolean) {
      loginProgressBar.visibility = View.VISIBLE
      loginButton.apply {
        text = ""
        isClickable = false
        isFocusable = false
      }
    } else {
      loginProgressBar.visibility = View.GONE
      loginButton.apply {
        text = buttonText
        isClickable = true
        isFocusable = true
      }
    }
  }

  companion object {
    private const val DEFAULT_LOGIN_TEXT = "Login"
  }
}
