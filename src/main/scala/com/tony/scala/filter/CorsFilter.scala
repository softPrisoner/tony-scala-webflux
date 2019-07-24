package com.tony.scala.filter

import org.springframework.context.annotation.Bean
import org.springframework.http.server.reactive.{ServerHttpRequest, ServerHttpResponse}
import org.springframework.http.{HttpHeaders, HttpMethod, HttpStatus}
import org.springframework.stereotype.Component
import org.springframework.web.cors.reactive.CorsUtils
import org.springframework.web.server.{ServerWebExchange, WebFilter, WebFilterChain}
import reactor.core.publisher.Mono

@Component
class CorsFilter {
  val ALLOWED_HEADERS: String = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client"
  val ALLOWED_METHODS: String = "*"
  val ALLOWED_ORIGIN: String = "*"
  val ALLOWED_Expose: String = "*"
  val MAX_AGE: String = "18000L"

  @Bean
  def filter(): WebFilter = {
    (ctx: ServerWebExchange, chain: WebFilterChain) => {
      val request: ServerHttpRequest = ctx.getRequest
      if (CorsUtils.isCorsRequest(request)) {
        val response: ServerHttpResponse = ctx.getResponse
        val headers: HttpHeaders = response.getHeaders
        headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN)
        headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS)
        headers.add("Access-Control-Max-Age", MAX_AGE)
        headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS)
        headers.add("Access-Control-Expose-Headers", ALLOWED_Expose)
        headers.add("Access-Control-Allow-Credentials", "true")
        if (request.getMethod == HttpMethod.OPTIONS) {
          response.setStatusCode(HttpStatus.OK)
          Mono.empty()
        }
      }
      chain.filter(ctx)
    }
  }
}
