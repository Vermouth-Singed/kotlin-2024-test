package com.example.sample.adapter.out.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "login_info")
data class LoginEntity (
    @Id
    val email: String,
)