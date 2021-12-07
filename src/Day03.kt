fun main() {
    fun part1(input: List<String>): Int {

        var gammaRate = ""
        var epsilonRate = ""
        val elementLength = input.first().length

        for (index in 0 until elementLength) {
            val ones = input.map { it[index] }.count { it == '1'}
            gammaRate += if (ones > input.size / 2) "1" else "0"
            epsilonRate += if (ones > input.size / 2) "0" else "1"
        }

//        println("Gamma Rate: $gammaRate")
//        println("Epsilon Rate: $epsilonRate")

        return Integer.parseInt(gammaRate, 2) * Integer.parseInt(epsilonRate, 2)
    }

    fun oxygenBitCriteria(input: List<String>, index: Int) : List<String> {
        val firstBitOne = input.filter { it[index] == '1' }
        val firstBitZero = input.filter { it[index] == '0' }

        return if (firstBitOne.size >= firstBitZero.size) {
            firstBitOne
        } else {
            firstBitZero
        }
    }

    fun cO2BitCriteria(input: List<String>, index: Int) : List<String> {
        val firstBitOne = input.filter { it[index] == '1' }
        val firstBitZero = input.filter { it[index] == '0' }

        return if (firstBitZero.size <= firstBitOne.size) {
            firstBitZero
        } else {
            firstBitOne
        }
    }


    fun part2(input: List<String>): Int {

        var oxygenGeneratorRating = ""
        var cO2ScrubberRating = ""
        val elementLength = input.first().length

        var partialResultOxygenRating = input
        var partialResultCO2Rating = input

        for (index in 0 until elementLength) {

            partialResultOxygenRating = oxygenBitCriteria(partialResultOxygenRating, index)
            partialResultCO2Rating = cO2BitCriteria(partialResultCO2Rating, index)

            if (partialResultOxygenRating.size == 1) {
                oxygenGeneratorRating = partialResultOxygenRating.first()
            }

            if (partialResultCO2Rating.size == 1) {
                cO2ScrubberRating = partialResultCO2Rating.first()

            }
        }

//        println(oxygenGeneratorRating)
//        println(CO2ScrubberRating)

        return Integer.parseInt(oxygenGeneratorRating, 2) * Integer.parseInt(cO2ScrubberRating, 2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}