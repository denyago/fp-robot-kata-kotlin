package com.finmid.fp.kata

import io.kotest.matchers.shouldBe
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

    @Test
    fun `direction changes the position to right`() {
        val position = Position(15, 15)
        val direction = Direction.RIGHT

        val result =
            parseDirection(position, direction)

        result shouldBe Position(16, 15)
    }
}
