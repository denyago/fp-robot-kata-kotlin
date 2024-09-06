package com.finmid.fp.kata

data class Map(
    val width: Int,
    val height: Int,
    val obstacles: List<Position>,
    val robot: Position,
)

data class Position(
    val x: Int,
    val y: Int,
)

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
}

/**
 * Parse a string to a map
 *
 * Example:
 *   R..T.
 *   .....
 *   ..T..
 *   .....
 *   .T...
 *
 *   T = Tree
 *   R = Robot
 *   . = Empty
 */
fun parseMap(input: String): Map {
    val lines = input.split("\n")
    val height = lines.size
    val width = lines[0].length
    var robotPosition: Position? = null
    val obstacles = mutableListOf<Position>()
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
            if (c == 'T') {
                obstacles.add(Position(x, y))
            } else if (c == 'R') {
                robotPosition = Position(x, y)
            }
        }
    }
    return Map(width, height, obstacles, requireNotNull(robotPosition))
}

/**
 * Parse a string to a list of commands
 *
 * Example:
 *   UDLR
 */
fun parseCommands(input: String): List<Direction> =
    input.map {
        when (it) {
            'U' -> Direction.UP
            'D' -> Direction.DOWN
            'L' -> Direction.LEFT
            'R' -> Direction.RIGHT
            else -> throw IllegalArgumentException("Unknown command: $it")
        }
    }

/**
 * Serialise a map to a string
 */
fun serialiseMap(map: Map): String {
    val lines = mutableListOf<String>()
    for (y in 0 until map.height) {
        val line = StringBuilder()
        for (x in 0 until map.width) {
            val position = Position(x, y)
            if (map.robot == position) {
                line.append('R')
            } else if (map.obstacles.contains(position)) {
                line.append('T')
            } else {
                line.append('.')
            }
        }
        lines.add(line.toString())
    }
    return lines.joinToString("\n")
}
