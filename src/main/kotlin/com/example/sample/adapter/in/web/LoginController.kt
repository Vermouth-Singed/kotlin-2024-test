package com.example.sample.adapter.`in`.web

import com.example.sample.adapter.`in`.web.dto.LoginRequestDTO
import com.example.sample.application.LoginService
import com.example.sample.application.port.`in`.LogInUseCase
import com.example.sample.application.port.`in`.LogOutUseCase
import com.example.sample.application.port.`in`.LoginCheckUseCase
import com.example.sample.domain.LoginEnum
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/login")
class LoginController (
    private val loginService: LoginService
) {

    @GetMapping
    fun loginCheck(httpSession: HttpSession): LoginCheckUseCase.Result {
        return loginService.loginCheck(httpSession.getAttribute(LoginEnum.LOGIN_INFO.code()))
    }

    @PostMapping
    fun login(httpSession: HttpSession, @RequestBody loginRequestDTO: LoginRequestDTO): LogInUseCase.Result {
        return loginService.login(httpSession, loginRequestDTO)
    }

    @DeleteMapping
    fun logout(httpSession: HttpSession): LogOutUseCase.Result {
        return loginService.logout(httpSession)
    }
}