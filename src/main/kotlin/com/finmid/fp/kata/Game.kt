package com.finmid.fp.kata

fun moveRobot(
    textMap: String,
    moveCommands: String,
): String = serialiseMap(
    parseCommands(moveCommands).fold(parseMap(textMap)) { map, direction ->
        executeStep(map, direction)
    }
)

fun executeStep(
    map: Map,
    direction: Direction,
): Map = goalPosition(map, direction)?.let { goalPos ->
    map.copy(robot = map.robot.smartMove(map, goalPos, direction))
} ?: map

fun Position.isTree(obstacles: List<Position>): Boolean = obstacles.contains(this)
fun Position.isInsideMap(map: Map): Boolean = x in 0 until map.width && y in 0 until map.height
fun goalPosition(map: Map, direction: Direction): Position? =
    goalPosition(map, map.robot.move(direction), direction)

fun goalPosition(map: Map, position: Position, direction: Direction): Position? {
    return if (!position.isTree(map.obstacles) && position.isInsideMap(map)) {
        position
    } else if (!position.isInsideMap(map)) {
        null
    } else {
        goalPosition(map, position.move(direction), direction)
    }
}

fun Position.move(direction: Direction): Position = when (direction) {
    Direction.UP -> copy(y = y - 1)
    Direction.DOWN -> copy(y = y + 1)
    Direction.LEFT -> copy(x = x - 1)
    Direction.RIGHT -> copy(x = x + 1)
}

// TODO: Replace `move` with the `smartMove` implementation
fun Position.smartMove(map: Map, goalPosition: Position, direction: Direction): Position = move(direction)