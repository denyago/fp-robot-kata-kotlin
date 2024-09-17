package com.finmid.fp.kata

import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameTest {

    @Test
    fun `should be able to apply command`() {
        assertSoftly {
            Source.entries.forEach {
                println(it.name)
                parseMap(it.initMap).applyCommand(it.command).serialize().shouldBe(it.resultMap)
            }
        }
    }

    enum class Source(val command: Direction, val initMap: String, val resultMap: String) {
        LEFT(Direction.LEFT, ".R.", "R.."),
        RIGHT(Direction.RIGHT, "R..", ".R."),
        UP(
            Direction.UP,
            """
            .
            R
            .
        """.trimIndent(),
            """
            R
            .
            .
        """.trimIndent()
        ),
        DOWN(
            Direction.DOWN,
            """
            R
            .
            .
        """.trimIndent(),
            """
            .
            R
            .
        """.trimIndent()
        ),
        RIGTH_WALL(Direction.RIGHT, "..R", "..R"),
        LEFT_WALL(Direction.LEFT, "R..", "R.."),
        UP_WALL(Direction.UP,
            """
                R
                .
                .
            """.trimIndent(),
            """
                R
                .
                .
            """.trimIndent()
        ),
        DOWN_WALL(Direction.DOWN,
            """
                .
                .
                R
            """.trimIndent(),
            """
                .
                .
                R
            """.trimIndent()),
    }
}