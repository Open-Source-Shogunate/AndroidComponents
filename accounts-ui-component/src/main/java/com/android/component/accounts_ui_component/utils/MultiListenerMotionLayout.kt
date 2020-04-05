package com.android.component.accounts_ui_component.utils

/**
 * Created by: ebaylon.
 * Created on: 21/03/2020.
 * Copyright by Note-e-fied Philippines Incorporated, 2020.
 */
import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import java.util.concurrent.CopyOnWriteArrayList

class MultiListenerMotionLayout @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr) {

  private val listeners = CopyOnWriteArrayList<TransitionListener>()

  /**
   * Add a [TransitionListener] which will be called as appropriate.
   */
  fun addTransitionListener(listener: TransitionListener) {
    listeners.addIfAbsent(listener)
  }

  /**
   * Remove a previously added [TransitionListener].
   */
  fun removeTransitionListener(listener: TransitionListener) {
    listeners.remove(listener)
  }

  init {
    super.setTransitionListener(object : TransitionListener {
      override fun onTransitionTrigger(
        motionLayout: MotionLayout,
        triggerId: Int,
        positive: Boolean,
        progress: Float
      ) {
        listeners.forEach {
          it.onTransitionTrigger(motionLayout, triggerId, positive, progress)
        }
      }

      override fun onTransitionStarted(motionLayout: MotionLayout, startId: Int, endId: Int) {
        listeners.forEach {
          it.onTransitionStarted(motionLayout, startId, endId)
        }
      }

      override fun onTransitionChange(
        motionLayout: MotionLayout,
        startId: Int,
        endId: Int,
        progress: Float
      ) {
        listeners.forEach {
          it.onTransitionChange(motionLayout, startId, endId, progress)
        }
      }

      override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
        listeners.forEach {
          it.onTransitionCompleted(motionLayout, currentId)
        }
      }
    })
  }
}