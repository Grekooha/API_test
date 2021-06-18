package com.company

import io.restassured.RestAssured
import io.restassured.RestAssured._
import io.restassured.module.scala.RestAssuredSupport.AddThenToResponse

class PetApi {
  RestAssured.baseURI = "https://petstore.swagger.io"
  RestAssured.basePath = "/v2/"

  def isExist(id: Int): PetApi = {
    when().get(s"/pet/$id")
      .then()
      .statusCode(200)
    this
  }

  def isNotExist(id: Int): PetApi = {
    when().get(s"/pet/$id")
      .then()
      .statusCode(404)
    this
  }

  def getPetName(id: Int): PetApi = {
    when().get(s"/pet/$id")
      .jsonPath.getString("name")
    this
  }

  def addPet(id: Int): PetApi = {
    val jsonFile: String = s"{\n  \"id\": $id,\n  \"category\": {\n    \"id\": 0,\n   " +
      " \"name\": \"string\"\n  },\n  \"name\": \"doggie\",\n  \"photoUrls\": [\n    " +
      "\"string\"\n  ],\n  \"tags\": [\n    {\n      \"id\": 0,\n      \"name\": \"string\"\n    }\n  ],\n  " +
      "\"status\": \"available\"\n}"

    given()
      .body(jsonFile)
      .contentType("application/json")
      .when()
      .post("/pet")
      .Then()
      .assertThat()
      .contentType("application/json")
      .and()
      .statusCode(200)
    this
  }
}
