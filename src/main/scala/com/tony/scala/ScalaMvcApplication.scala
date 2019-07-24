package com.tony.scala

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ImportResource
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
class ScalaMvcApplication

object ScalaMvcApplication {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[ScalaMvcApplication], args: _*)
  }

}