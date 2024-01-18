package com.example.sample.adapter.`in`.web

import com.example.sample.application.SampleRetrofitService
import com.example.sample.application.port.`in`.LoginCheckUseCase
import com.example.sample.util.RetrofitConnection
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/sample")
class SampleRetrofitController {

    @GetMapping
    fun sample(): LoginCheckUseCase.Result {
        val retrofitApi = RetrofitConnection
            .getInstance("http://localhost:8080/")
            .create(SampleRetrofitService::class.java)
            .test()
            .execute()

        return LoginCheckUseCase.Result(email = retrofitApi.body().orEmpty(), success = retrofitApi.isSuccessful)
    }

    @GetMapping("/test")
    fun test(): String = "test"
}