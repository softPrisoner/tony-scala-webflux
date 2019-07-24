package com.tony.scala.json

import java.util

import com.google.gson.{Gson, GsonBuilder}
import org.junit.Test


class JsonTest {
  @Test
  def test(): Unit = {
    val user: TestUser = new TestUser(1, "2")
    //Gson
    val builder: GsonBuilder = new GsonBuilder()
    val gson: Gson = builder.create()
    val list: util.List[TestUser] = new util.ArrayList[TestUser]()
    list.add(user)
    list.add(user)
    var obj: util.Iterator[TestUser] = list.iterator()
    while (obj.hasNext) {
      val user: TestUser = obj.next()
      print(user)
    }
    val str: String = gson.toJson(list)
    val c = List(user, user, user, user, user)
    //    println("111" + c.head.toString)
    println(str)
    val tt: util.ArrayList[TestUser] = gson.fromJson(str, classOf[util.ArrayList[TestUser]])
    val t: String = gson.toJson(List(user, user, user, user, user))

    println(t)
    println(tt)
    //    val list1: util.List[TestUser] = gson.fromJson(t, classOf[util.ArrayList[TestUser]])
    //    val list2: util.List[TestUser] = JSON.parseObject(t, classOf[TestUser])
    //    val list3: util.List[TestUser] = parse(t).extract[util.List[TestUser]]
    //    val user1: TestUser = list1.get(1)
    //    val user2: TestUser = list2.get(1)
    //    val user3: TestUser = list3.get(1)
    //    println(user1)
    //    println(user2)
    //    println(user3)
  }
}
