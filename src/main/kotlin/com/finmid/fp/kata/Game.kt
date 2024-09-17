package com.finmid.fp.kata


@Suppress("UNUSED_PARAMETER")
fun moveRobot(
    textMap: String,
    moveCommands: String,
): String {
    return serialiseMap(moveRobot(parseMap(textMap), parseCommands(moveCommands), 0))
}

fun moveRobot(map: Map, commands: List<Direction>, curCommand: Int): Map {
    if (curCommand >= commands.size) {
        return map
    }

    return moveRobot(map.copy(robot = validatePosition(map, parseDirection(map.robot, commands.get(curCommand)), map.robot, map.obstacles, commands.get(curCommand))), commands, curCommand + 1)
}

fun validatePosition(map: Map, newPosition: Position, curPosition: Position, obstacles: List<Position>, command: Direction): Position =
    if (!obstacles.contains(newPosition) && newPosition.x >= 0 && newPosition.x < map.width && newPosition.y >= 0 && newPosition.y < map.height) {
        newPosition
    } else if (obstacles.contains(newPosition) && (
                (newPosition.x == 0 && command == Direction.LEFT)
                        || (newPosition.x == map.width -1 && command == Direction.RIGHT)
                        || (newPosition.y == 0 && command == Direction.UP)
                        || (newPosition.y == map.height - 1 && command == Direction.DOWN)
        )) {
        curPosition
    } else {
        validatePosition(map, parseDirection(newPosition, command), curPosition, obstacles, command)
    }



fun parseDirection(position: Position, direction: Direction): Position =
    when (direction) {
        Direction.UP -> Position(position.x, position.y - 1)
        Direction.DOWN -> Position(position.x, position.y + 1)
        Direction.LEFT -> Position(position.x - 1, position.y)
        Direction.RIGHT -> Position(position.x + 1, position.y)
}
