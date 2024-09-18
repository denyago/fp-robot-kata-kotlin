package com.finmid.fp.kata

fun moveRobot(
    textMap: String,
    moveCommands: String,
): String =
    parseCommands(moveCommands)
        .fold(parseMap(textMap)) { acc, command -> move2(acc, command) ?: acc }
        .let {
            println(it)
            serialiseMap(it)
        }

fun move(
    map: Map,
    direction: Direction
): Map = if (validateMovement2(map, direction)) {
    map.copy(robot = calculateNextPosition(map.robot, direction))
} else map

fun move2(
    map: Map,
    direction: Direction
): Map? =
    map.copy(robot = calculateNextPosition(map.robot, direction)).let {
        when {
            isOutsideWalls(it) -> null
            hitTheTree(it) -> move2(it, direction)
            else -> it
        }
    }

fun calculateNextPosition(
    position: Position,
    direction: Direction,
): Position  = when(direction) {
        Direction.UP -> position.copy(y = position.y - 1)
        Direction.DOWN -> position.copy(y = position.y + 1)
        Direction.RIGHT -> position.copy(x = position.x + 1)
        Direction.LEFT -> position.copy(x = position.x - 1)
    }

fun validateMovement2(
    map: Map,
    direction: Direction,
): Boolean = map.copy(robot = calculateNextPosition(map.robot, direction)).let {
    !isOutsideWalls(it) && !hitTheTree(it)
}

fun isOutsideWalls(map: Map) : Boolean =
     map.robot.y < 0 ||
     map.robot.y > map.height - 1 ||
     map.robot.x > map.width - 1 ||
     map.robot.x < 0

fun hitTheTree(
    map: Map,
): Boolean = map.obstacles.any { map.robot == it }


