package com.android.component.authentication_api_component

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

/**
 * Created by: ebaylon.
 * Created on: 18/03/2020.
 * Copyright by Note-e-fied Philippines Incorporated, 2020.
 */
class FireBaseAuthentication(private val firebaseAuth: FirebaseAuth) {

  fun verifyPhone(
    phoneNumber: String,
    activity: Activity,
    onVerificationComplete: (user: FirebaseUser?) -> Unit,
    onVerificationFailed: (exception: String?) -> Unit,
    onCodeVerification: (verificationId: String) -> Unit,
    timeout: Long = 60
  ) {
    val phoneAuthOptions = PhoneAuthOptions.newBuilder()
      .setActivity(activity)
      .setTimeout(timeout, TimeUnit.SECONDS)
      .setPhoneNumber(phoneNumber)
      .setCallbacks(
        getPhoneVerificationCallback(
          onVerificationComplete,
          onVerificationFailed,
          onCodeVerification
        )
      )
      .build()
    PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
  }

  fun verifyCode(
    verificationId: String,
    code: String,
    onVerificationComplete: (user: FirebaseUser?) -> Unit,
    onVerificationFailed: (exception: String?) -> Unit
  ) {
    val credential = PhoneAuthProvider.getCredential(verificationId, code)
    signInWithCredential(credential, onVerificationComplete, onVerificationFailed)
  }

  private fun getPhoneVerificationCallback(
    onVerificationComplete: (user: FirebaseUser?) -> Unit,
    onVerificationFailed: (exception: String?) -> Unit,
    onCodeVerification: (verificationId: String) -> Unit
  ): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
    return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
      override fun onVerificationCompleted(p0: PhoneAuthCredential) {
        signInWithCredential(p0, onVerificationComplete, onVerificationFailed)
      }

      override fun onVerificationFailed(p0: FirebaseException) {
        onVerificationFailed(p0.localizedMessage)
      }

      override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
        super.onCodeSent(p0, p1)
        onCodeVerification(p0)
      }
    }
  }

  private inline fun signInWithCredential(
    credential: AuthCredential,
    crossinline onVerificationComplete: (user: FirebaseUser?) -> Unit,
    crossinline onVerificationFailed: (exception: String?) -> Unit
  ) {
    // sign in with firebase
    firebaseAuth.signInWithCredential(credential)
      .addOnCompleteListener { task ->
        if (task.isSuccessful) {
          onVerificationComplete(task.result?.user)
        } else {
          onVerificationFailed(task.exception?.localizedMessage)
        }
      }
  }
}