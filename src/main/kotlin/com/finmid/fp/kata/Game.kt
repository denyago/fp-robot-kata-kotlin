package com.finmid.fp.kata

fun moveRobot(
    textMap: String,
    moveCommands: String,
): String =
    parseCommands(moveCommands)
        .fold(parseMap(textMap)) { acc, command -> move(acc, command) }
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

fun calculateNextPosition(
    position: Position,
    direction: Direction,
): Position  = when(direction) {
        Direction.UP -> position.copy(y = position.y - 1)
        Direction.DOWN -> position.copy(y = position.y + 1)
        Direction.RIGHT -> position.copy(x = position.x + 1)
        Direction.LEFT -> position.copy(x = position.x - 1)
    }

fun validateMovement(
    map: Map,
    direction: Direction,
): Boolean = validateWalls(map, direction) && validateTrees(map, direction)

fun validateMovement2(
    map: Map,
    direction: Direction,
): Boolean = map.copy(robot = calculateNextPosition(map.robot, direction)).let {
    !isOutsideWalls(it) && !hitTheTree(it)
}

private fun validateWalls(map: Map, direction: Direction) = when (direction) {
    Direction.UP -> map.robot.y > 0
    Direction.DOWN -> map.robot.y < map.height - 1
    Direction.RIGHT -> map.robot.x < map.width - 1
    Direction.LEFT -> map.robot.x > 0
}

fun isOutsideWalls(map: Map) : Boolean =
     map.robot.y < 0 ||
     map.robot.y > map.height - 1 ||
     map.robot.x > map.width - 1 ||
     map.robot.x < 0

fun validateTrees(
    map: Map,
    direction: Direction,
): Boolean = calculateNextPosition(map.robot, direction).let { robotNewPosition ->
    map.obstacles.none { robotNewPosition == it }
}

fun hitTheTree(
    map: Map,
): Boolean = map.obstacles.any { map.robot == it }


