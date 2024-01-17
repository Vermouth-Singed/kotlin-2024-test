package com.example.sample.adapter.out.persistence.repository

import com.example.sample.adapter.out.persistence.entity.LoginEntity
import com.example.sample.adapter.out.persistence.entity.QLoginEntity.loginEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class LoginQuerydsl (
    private val jpaQueryFactory: JPAQueryFactory,
) {
    fun findById(email: String): LoginEntity {
        val list: List<LoginEntity> = jpaQueryFactory
            .selectFrom(loginEntity)
            .where(loginEntity.email.eq(email))
            .limit(1)
            .fetch()

        return if (list.isNotEmpty()) list.get(0) else LoginEntity(email = "")
    }
}