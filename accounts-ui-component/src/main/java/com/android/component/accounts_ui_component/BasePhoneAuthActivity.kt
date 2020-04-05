package com.android.component.accounts_ui_component

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.android.component.accounts_ui_component.utils.MultiListenerMotionLayout
import com.android.component.accounts_ui_component.utils.awaitTransitionComplete
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.activity_base_phone_auth_landing.*
import kotlinx.coroutines.delay

open class BasePhoneAuthActivity : AppCompatActivity() {

  val backgroundLayout: MultiListenerMotionLayout by lazy { motionLayout }
  val appLogo: ImageView by lazy { imageAppLogo }
  val numberLabel: MaterialTextView by lazy { labelNumber }
  val numberInputLayout: TextInputLayout by lazy { inputLayoutNumber }
  val numberInput: TextInputEditText by lazy { inputNumber }
  val loginButton: Button by lazy { buttonLogin }
  val signUpButton: Button by lazy { buttonSignUp }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base_phone_auth_landing)

  }

  suspend fun loadLanding(duration: Int = DEFAULT_DURATION, delay: Long = DEFAULT_DELAY) {
    motionLayout.apply {
      setTransition(R.id.landing, R.id.landing)
      setTransitionDuration(duration)
      transitionToEnd()
      awaitTransitionComplete(R.id.landing)
      delay(delay)
    }
  }

  suspend fun landingToLogin(duration: Int = DEFAULT_DURATION, delay: Long = DEFAULT_DELAY) {
    motionLayout.apply {
      setTransition(R.id.landing, R.id.login)
      setTransitionDuration(duration)
      transitionToEnd()
      awaitTransitionComplete(R.id.login)
      delay(delay)
    }
  }

  suspend fun loginToLanding(duration: Int = DEFAULT_DURATION, delay: Long = DEFAULT_DELAY) {
    motionLayout.apply {
      setTransition(R.id.login, R.id.landing)
      setTransitionDuration(duration)
      transitionToEnd()
      awaitTransitionComplete(R.id.landing)
      delay(delay)
    }
  }

  companion object {
    private const val DEFAULT_DURATION = 300
    private const val DEFAULT_DELAY = 1500L
  }
}
