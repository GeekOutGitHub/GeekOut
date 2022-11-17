package com.example.geekouttry

data class Account (val email: String = "", val nickName: String = "",
                    val accountID: String = "", var points: Int = 0, var status: String = "OFF")