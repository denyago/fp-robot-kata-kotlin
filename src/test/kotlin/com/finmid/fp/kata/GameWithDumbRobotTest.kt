package com.finmid.fp.kata

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameWithDumbRobotTest {
    @Test
    fun `robot moves`() {
        val result =
            rideRobot(
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
            rideRobot(
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
            rideRobot(
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
    fun `robot moves and hits the tree`() {
        val result =
            rideRobot(
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
