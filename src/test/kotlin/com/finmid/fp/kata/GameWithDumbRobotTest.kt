package com.finmid.fp.kata

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class GameWithDumbRobotTest {
    @Test
    fun `robot moves`() {
        val result =
            moveRobot(
                """
                R..
                ...
                ...
                """.trimIndent(),
                "R",
            )

        result shouldBe
            """
            .R.
            ...
            ...
            """.trimIndent()
    }

    @Test
    fun `robot moves multiple times`() {
        val result =
            moveRobot(
                """
                R..
                ...
                ...
                """.trimIndent(),
                "RDRDLU",
            )

        result shouldBe
            """
            ...
            .R.
            ...
            """.trimIndent()
    }

    @Test
    fun `robot moves multiple times and hits the wall`() {
        val result =
            moveRobot(
                """
                R..
                ...
                ...
                """.trimIndent(),
                "RRRRRRRR",
            )

        result shouldBe
            """
            ..R
            ...
            ...
            """.trimIndent()
    }

    @Test
    @Disabled("Incorrect for the smart robot implementation")
    fun `robot moves and hits the tree`() {
        val result =
            moveRobot(
                """
                R..
                .T.
                ...
                """.trimIndent(),
                "RD",
            )

        result shouldBe
            """
            .R.
            .T.
            ...
            """.trimIndent()
    }
}
