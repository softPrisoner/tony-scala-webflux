package com.tony.scala.route

import com.tony.scala.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.{RequestPredicates, RouterFunction, RouterFunctions, ServerResponse}

@Component
class UserRouteConfig {

  @Bean
  def route(userHandler: UserHandler): RouterFunction[ServerResponse] = {

    RouterFunctions.route(RequestPredicates.POST("/reg")
      .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler.register)

      .andRoute(RequestPredicates.POST("/login")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler.register)

      .andRoute(RequestPredicates.POST("/delete/{id}")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler.deleteUser)

      .andRoute(RequestPredicates.GET("/get/all")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler.getAllUser)

      .andRoute(RequestPredicates.GET("/get/{id}")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler.getUserById)

  }
}
