package com.finmid.fp.kata

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameWithSmartRobotTest {
    @Test
    fun `robot moves and walks around the tree`() {
        val result =
            rideRobot(
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
            rideRobot(
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
            rideRobot(
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
            .T..
            ..R.
            """.trimIndent()
    }

    @Test
    fun `robot does not move if there are no free spaces`() {
        val result =
            rideRobot(
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
