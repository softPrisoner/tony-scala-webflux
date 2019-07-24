package com.tony.scala.handler

import com.alibaba.fastjson.JSON
import com.tony.scala.domain.{User, UserResponseBody}
import com.tony.scala.http.ResCode
import com.tony.scala.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.{Criteria, Query}
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.{ServerRequest, ServerResponse}
import reactor.core.publisher.Mono

@Component
class UserHandler(@Autowired userRepository: UserRepository,
                  @Autowired mongoTemplate: MongoTemplate) {
  /** 用户注册 **/
  def register(request: ServerRequest): Mono[ServerResponse] = {

    request.bodyToMono(classOf[User]).subscribe(item => userRepository.save(item)).dispose()
    ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
      .body(Mono.just(new UserResponseBody(ResCode.SUCCESS, "注册成功")), classOf[UserResponseBody])
  }

  /** 用户登录 **/
  def login(request: ServerRequest): Mono[ServerResponse] = {
    var body = new UserResponseBody(ResCode.FAILED, "用户名或密码不正确")
    request.bodyToMono(classOf[User]).subscribe(u => {
      //      val user: Mono[User] = userRepository.findOne(Example.of(User))
      val query: Query = new Query(Criteria.where("username").is(u.getUsername).and("password").is(u.getPassword))
      val user: User = mongoTemplate.findOne(query, classOf[User], "user")
      if (null != user) {
        body = new UserResponseBody(ResCode.SUCCESS, "登录成功")
      }
    })
    ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(body), classOf[UserResponseBody])
  }

  /** 获取所有用户 **/
  def getUserById(request: ServerRequest): Mono[ServerResponse] = {
    val id = request.pathVariable("id").toLong
    val body = new UserResponseBody(ResCode.SUCCESS, userRepository.findById(id))
    ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.justOrEmpty(body), classOf[UserResponseBody])
  }

  /** 获取所有用户 **/
  def getAllUser(request: ServerRequest): Mono[ServerResponse] = {
    val userList: Any = userRepository.findAll()
    ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.justOrEmpty(JSON.toJSONString(userList)), classOf[String])
  }

  /** 删除用户 **/
  def deleteUser(request: ServerRequest): Mono[ServerResponse] = {
    val id = request.pathVariable("id").toLong
    userRepository.deleteById(id)
    val body = new UserResponseBody(ResCode.SUCCESS, "删除成功")
    ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(body), classOf[UserResponseBody])
  }
}
