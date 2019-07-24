package com.tony.scala.repository

import com.tony.scala.domain.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Component

@Component
trait UserRepository extends ReactiveMongoRepository[User, Long]
