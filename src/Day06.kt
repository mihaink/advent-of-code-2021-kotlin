fun main() {
    fun part1(input: List<String>): Int {
        var numericInput = input.first().split(',').map { it.toInt() }.toMutableList()

        var babyLanternFish = 0
        repeat(80) {
            numericInput = numericInput.map { it - 1 }.toMutableList()
            babyLanternFish = numericInput.count { it == -1 }
            numericInput = numericInput.map { if (it == -1) 6 else it }.toMutableList()
            numericInput.addAll(List(babyLanternFish) { 8 })
        }

        return numericInput.size
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
//    check(part2(testInput) == 26984457539)
//
    val input = readInput("Day06")
    println(part1(input))
//    println(part2(input))
}