package com.example.sample.adapter.out.persistence.repository

import com.example.sample.adapter.out.persistence.entity.LoginEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LoginJPA : JpaRepository<LoginEntity, String>