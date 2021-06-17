package com.company

import java.util

import io.restassured.RestAssured.given
import io.restassured.module.scala.RestAssuredSupport.AddThenToResponse
import org.junit.Test

class Mmain {
  private val url = "https://petstore.swagger.io/v2/pet"

  @Test def crudTest(): Unit = {
    val jsonAsMap = new util.HashMap[String, Any]
    jsonAsMap.put("id", 2002)
    jsonAsMap.put("name", "nn")

    val putId = given()
      .body(jsonAsMap.toString)
      .contentType("application/json")
      .when()
      .post(url)
      .Then()
      .assertThat()
      .contentType("application/json")
      .and()
      .statusCode(200)
    println(putId)
  }
}