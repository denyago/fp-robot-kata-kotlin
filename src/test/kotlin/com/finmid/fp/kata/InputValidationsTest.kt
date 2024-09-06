package com.finmid.fp.kata

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class InputValidationsTest {
    @Test
    fun `rideRobot should return an errors when the inputs empty`() {
        rideRobot("", "") shouldBe "Errors: Empty map, No move commands"
    }

    @Test
    fun `rideRobot should return an error when no robot is present in the map`() {
        rideRobot(".", "R") shouldBe "Errors: No robot found"
    }

    @Test
    fun `rideRobot should return an errors when no move commands are present`() {
        rideRobot("R", "NSEW") shouldBe "Errors: " +
            "N is not a valid command, " +
            "S is not a valid command, " +
            "E is not a valid command, " +
            "W is not a valid command"
    }
}
