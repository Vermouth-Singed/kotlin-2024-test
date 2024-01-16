package com.example.sample.application

import com.example.sample.adapter.`in`.web.dto.LoginRequestDTO
import com.example.sample.adapter.out.persistence.entity.LoginEntity
import com.example.sample.adapter.out.persistence.repository.LoginJPA
import com.example.sample.application.port.`in`.LogInUseCase
import com.example.sample.application.port.`in`.LogOutUseCase
import com.example.sample.application.port.`in`.LoginCheckUseCase
import com.example.sample.domain.LoginEnum
import com.example.sample.domain.LoginVO
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Service

@Service
class LoginService (
    private val loginJPA: LoginJPA,
): LoginCheckUseCase, LogInUseCase, LogOutUseCase {

    override fun loginCheck(loginVO: Any?): LoginCheckUseCase.Result {
        if (loginVO is LoginVO) {
            return LoginCheckUseCase.Result(email = loginVO.email, success = true)
        }

        return LoginCheckUseCase.Result(email = "", success = false)
    }

    override fun login(httpSession: HttpSession, loginRequestDTO: LoginRequestDTO): LogInUseCase.Result {
        val loginEntity: LoginEntity = loginJPA.findById(loginRequestDTO.email).orElse(LoginEntity(email = ""))

        val email: String = loginEntity.email
        val success: Boolean = email != ""

        if (success) {
            httpSession.setAttribute(LoginEnum.LOGIN_INFO.code(), LoginVO(email = email))
        } else {
            httpSession.invalidate()
        }

        return LogInUseCase.Result(email = email, success = success)
    }

    override fun logout(httpSession: HttpSession): LogOutUseCase.Result {
        httpSession.invalidate()

        return LogOutUseCase.Result(success = true)
    }
}