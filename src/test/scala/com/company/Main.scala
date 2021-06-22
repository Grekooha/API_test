package com.company

import org.scalatest.flatspec.AnyFlatSpec

class Main extends AnyFlatSpec {

  val idExist: Int = 222
  val idNotExist: Int = 0

  val api = new PetApi()

  it should "add a new pet with id" in {
    api
      .addPet(idExist)
  }

  it should "pet by id is exist" in {
    api
      .isExist(idExist)
  }

  it should "pet by id is not exist" in {
    api
      .isNotExist(idNotExist)
  }

  it should "print pet's name by id" in {

    api
      .getPetName(idExist)
  }

  it should "available pet's" in {

    api
      .petIsAvailable(idExist)
  }
  it should "10 available pet's" in {

    api
      .getAvailablePet(10)
  }
}