package com.finmid.fp.kata

@Suppress("UNUSED_PARAMETER")
fun moveRobot(
    textMap: String,
    moveCommands: String,
): String = parseMap(textMap).applyCommands(parseCommands(moveCommands)).serialize()

private fun Map.applyCommands(parsedCommands: List<Direction>): Map = parsedCommands
    .fold(this) { map, command ->
        map.applyCommand(command)
    }

private fun Map.applyCommand(command: Direction): Map = when (command) {
    Direction.UP -> this.copy(robot = this.robot.copy(y = this.robot.y + 1))
    Direction.DOWN -> this.copy(robot = this.robot.copy(y = this.robot.y - 1))
    Direction.LEFT -> this.copy(robot = this.robot.copy(x = this.robot.x - 1))
    Direction.RIGHT -> this.copy(robot = this.robot.copy(x = this.robot.x + 1))
}


private fun Map.serialize(): String = serialiseMap(this)
