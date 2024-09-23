package com.finmid.fp.kata

@Suppress("UNUSED_PARAMETER")
fun moveRobot(
    textMap: String,
    moveCommands: String,
): String {
    return combine(
        { getValidMap(textMap) },
        { getValidCommands(moveCommands) },
        ::moveRobot
    )
}

typealias Result<T> = Pair<T?, Error?>

data class Error(
    val value: String
) {
    override fun toString(): String {
        return value
    }
}

fun getValidMap(textMap: String): Result<Map> {
    if (textMap.isEmpty()) {
        return null to Error("Empty map")
    }

    if (!textMap.contains('R')) {
        return null to Error("No robot found")
    }

    return parseMap(textMap) to null
}

fun getValidCommands(moveCommands: String): Result<List<Direction>> {
    if (moveCommands.isEmpty()) {
        return null to Error("No move commands")
    }

    if (moveCommands.any { it !in "UDLR".toSet() }) {
        return null to Error(getInvalidCommands(emptyList(), moveCommands).joinToString())
    }

    return parseCommands(moveCommands) to null
}

fun getInvalidCommands(result: List<String>, directions: String): List<String> {
    if (directions.isEmpty()) {
        return result
    }

    return getInvalidCommands(result + "${directions[0]} is not a valid command", directions.drop(1))
}

fun moveRobot(
    map: Result<Map>,
    commands: Result<List<Direction>>,
): String {
    if (map.second != null || commands.second != null) {
        return "Errors: ${listOfNotNull(map.second, commands.second).joinToString(", ")}"
    }

    if (map.first == null) {
        return "Errors: Internal error"
    }

    return serialiseMap(move(map.first!!, commands.first!!))
}

tailrec fun move(
    map: Map,
    commands: List<Direction>,
): Map {
    if (commands.isEmpty()) return map

    return move(makeMove(map, commands.first()), commands.drop(1))
}

fun makeMove(
    map: Map,
    direction: Direction,
): Map {
    if (isNextStepIsWall(map, direction)) {
        return map
    }

    if (isNextStepIsTree(map, direction)) {
        return map
    }

    return map.copy(robot = map.robot.getNewPosition(direction))
}

fun isNextStepIsWall(map: Map, direction: Direction): Boolean {
    return validatePosition(map, map.robot.getNewPosition(direction))
}

fun validatePosition(map: Map, position: Position): Boolean {
    if (position.x < 0 || position.x >= map.width || position.y < 0 || position.y >= map.height) {
        return true
    }

    return false
}

fun isNextStepIsTree(map: Map, direction: Direction): Boolean {
    return validatePosition(map.obstacles, map.robot.getNewPosition(direction))
}

fun validatePosition(obstacles: List<Position>, position: Position): Boolean {
    return obstacles.contains(position)
}

fun Position.getNewPosition(direction: Direction) = when (direction) {
        Direction.RIGHT -> Position(x + 1, y)
        Direction.LEFT -> Position(x - 1, y)
        Direction.DOWN -> Position(x, y + 1)
        Direction.UP -> Position(x,  y - 1)
    }


fun <A, B, C> combine(
    a: () -> A,
    b: () -> B,
    f: (A, B) -> C
): C = f(a(), b())