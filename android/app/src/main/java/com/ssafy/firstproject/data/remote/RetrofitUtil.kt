package com.ssafy.firstproject.data.remote

import com.ssafy.firstproject.base.ApplicationClass

class RetrofitUtil {
    companion object {
        val customerService: UserService = ApplicationClass.retrofit.create(UserService::class.java)
    }
}