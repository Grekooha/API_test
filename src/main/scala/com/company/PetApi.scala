package com.company

import io.restassured.RestAssured
import io.restassured.RestAssured._
import io.restassured.module.scala.RestAssuredSupport.AddThenToResponse

class PetApi {
  RestAssured.baseURI = "https://petstore.swagger.io"
  RestAssured.basePath = "/v2/"

  def a(id: Int): String = {
    when()
      .get(s"/pet/$id").print()
  }

  def isExist(id: Int): PetApi = {
    when()
      .get(s"/pet/$id")
      .then()
      .statusCode(200)
    this
  }

  def isNotExist(id: Int): PetApi = {
    when()
      .get(s"/pet/$id")
      .then()
      .statusCode(404)
    this
  }

  def getPetName(id: Int): PetApi = {
    val petName: String = when()
      .get(s"/pet/$id")
      .then()
      .extract()
      .jsonPath.getString("name")
    println(petName)
    this
  }

  def petIsAvailable(id: Int): String = {
    when()
      .get(s"/pet/$id")
      .then()
      .extract()
      .jsonPath.getString("status")
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

  def getAvailablePet(quantity: Int): PetApi = {

    val list: Array[String] = when()
      .get("/pet/findByStatus?status=available")
      .jsonPath.getString("name")
      .replaceAll("\\W", " ")
      .split("\\s")
      .filter(_.nonEmpty)

    println(list.take(quantity).mkString("Available pet: ", ", ", ""))
    this
  }
}