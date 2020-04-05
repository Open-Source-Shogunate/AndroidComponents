package com.android.component.accounts_ui_component.utils

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import java.util.concurrent.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by: ebaylon.
 * Created on: 21/03/2020.
 * Copyright by Note-e-fied Philippines Incorporated, 2020.
 */

/**
 * Wait for the transition to complete so that the given [transitionId] is fully displayed.
 *
 * @param transitionId The transition set to await the completion of
 * @param timeout Timeout for the transition to take place. Defaults to 5 seconds.
 */
suspend fun MultiListenerMotionLayout.awaitTransitionComplete(
  transitionId: Int,
  timeout: Long = 5000L
) {
  // If we're already at the specified state, return now
  if (currentState == transitionId) return

  var listener: MotionLayout.TransitionListener? = null

  try {
    suspendCoroutine<Unit> { continuation ->
      val l = object : TransitionAdapter() {
        override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
          if (currentId == transitionId) {
            removeTransitionListener(this)
            continuation.resume(Unit)
          }
        }
      }
      // If the coroutine is cancelled, remove the listener
      continuation.runCatching {
        removeTransitionListener(l)
      }
      // And finally add the listener
      addTransitionListener(l)
      listener = l
    }
  } catch (tex: CancellationException) {
    // Transition didn't happen in time. Remove our listener and throw a cancellation
    // exception to let the coroutine know
    listener?.let(::removeTransitionListener)
    throw CancellationException(
      "Transition to state with id: $transitionId did not" +
       " complete in timeout. ${tex.localizedMessage}"
    )
  }
}