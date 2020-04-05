package com.android.component.accounts_ui_component

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BaseLoginActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base_login)
  }
}
