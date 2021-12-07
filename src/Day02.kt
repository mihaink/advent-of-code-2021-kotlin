fun main() {
    fun part1(input: List<String>): Int {
        var verticalPosition = 0
        var horizontalPosition = 0

        for (command in input) {
            val commandSplit = command.split(' ')
            val direction = commandSplit[0]
            val distance = commandSplit[1].toInt()

            when (direction) {
                "forward" -> horizontalPosition += distance
                "up" -> verticalPosition -= distance
                "down" -> verticalPosition += distance
            }
        }

        return verticalPosition * horizontalPosition
    }

    fun part2(input: List<String>): Int {
        var aim = 0
        var verticalPosition = 0
        var horizontalPosition = 0

        for (command in input) {
            val commandSplit = command.split(' ')
            val direction = commandSplit[0]
            val distance = commandSplit[1].toInt()

            when (direction) {
                "down" -> aim += distance
                "up" -> aim -= distance
                "forward" -> {
                    horizontalPosition += distance
                    verticalPosition += aim * distance
                }
            }
        }

        return verticalPosition * horizontalPosition
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}