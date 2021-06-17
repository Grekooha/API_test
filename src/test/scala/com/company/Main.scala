package com.company

import org.scalatest.flatspec.AnyFlatSpec

class Main extends AnyFlatSpec {

  val page = new PetApi()

  it should "get pet by id" in {
    page
      .getPet(200)
      .getPet(404)
  }

  it should "get pet's name by id" in {
    page
      .getPetName(200)
      .getPetName(404)
  }

  it should "add a new pet whith id" in {
    page
      .post(200)
  }
}