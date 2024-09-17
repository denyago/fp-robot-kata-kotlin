package com.finmid.fp.kata

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameWithSmartRobotTest {
    @Test
    fun `robot moves and walks around the tree`() {
        val result =
            moveRobot(
                """
                .R.
                .T.
                ...
                """.trimIndent(),
                "D",
            )

        result shouldBe
            """
            ...
            .T.
            .R.
            """.trimIndent()
    }

    @Test
    fun `robot moves and walks around multiple trees`() {
        val result =
            moveRobot(
                """
                ..R
                .TT
                ...
                """.trimIndent(),
                "D",
            )

        result shouldBe
            """
            ...
            .TT
            ..R
            """.trimIndent()
    }

    @Test
    fun `robot moves and walks around forest of trees`() {
        val result =
            moveRobot(
                """
                ..R.
                .TT.
                ..TT
                .T..
                ....
                """.trimIndent(),
                "D",
            )

        result shouldBe
            """
            ....
            .TT.
            ..TT
            .TR.
            ....
            """.trimIndent()
    }

    @Test
    fun `robot does not move if there are no free spaces`() {
        val result =
            moveRobot(
                """
                RT
                TT
                """.trimIndent(),
                "D",
            )

        result shouldBe
            """
            RT
            TT
            """.trimIndent()
    }
}
