package com.huyhuynh.mypokedex.screen.main.activity.login

import androidx.databinding.ObservableField
import demo.com.weatherapp.screen.base.viewmodel.BaseViewModel
import javax.inject.Inject

class LoginActivityViewModel @Inject constructor(): BaseViewModel() {
    var email: ObservableField<String> = ObservableField()
    var pass: ObservableField<String> = ObservableField()

    init {

    }
}