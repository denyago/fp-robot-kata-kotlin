package com.finmid.fp.kata

fun moveRobot(
    textMap: String,
    moveCommands: String,
): String = parseMap(textMap).applyCommands(parseCommands(moveCommands)).serialize()

private fun Map.applyCommands(parsedCommands: List<Direction>): Map = parsedCommands
    .fold(this) { map, command ->
        map.applyCommand(command)
    }

fun Map.applyCommand(command: Direction): Map = when (command) {
    Direction.UP -> {
        (this.robot.y - 1)
            .takeUnless { it < 0 }
            ?.takeUnless { Position(this.robot.x, it).doesCollideWithObstacles(this.obstacles) }
            ?.let { this.copy(robot = this.robot.copy(y = it)) }
            ?: this
    }

    Direction.DOWN -> {
        (this.robot.y + 1)
            .takeUnless { it > this.height - 1 }
            ?.takeUnless { Position(this.robot.x, it).doesCollideWithObstacles(this.obstacles) }
            ?.let { this.copy(robot = this.robot.copy(y = it)) }
            ?: this
    }

    Direction.LEFT -> {
        (this.robot.x - 1)
            .takeUnless { it < 0 }
            ?.takeUnless { Position(it, this.robot.y).doesCollideWithObstacles(this.obstacles) }
            ?.let { this.copy(robot = this.robot.copy(x = it)) }
            ?: this
    }

    Direction.RIGHT -> {
        (this.robot.x + 1)
            .takeUnless { it > this.width - 1 }
            ?.takeUnless { Position(it, this.robot.y).doesCollideWithObstacles(this.obstacles) }
            ?.let { this.copy(robot = this.robot.copy(x = it)) }
            ?: this
    }
}

fun Map.serialize(): String = serialiseMap(this)

private fun Position.doesCollideWithObstacles(obstacles: List<Position>) = this in obstacles
