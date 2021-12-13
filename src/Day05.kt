import java.lang.Integer.min
import java.lang.Integer.max
import kotlin.math.abs

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    fun getPoints(): List<Pair<Int, Int>> {
        return if (x1 == x2 && y1 != y2) {
            List(abs(y1-y2) + 1) { x1 } zip min(y1,y2)..max(y1, y2)
        } else if (x1 != x2 && y1 == y2) {
            min(x1, x2)..max(x1, x2) zip List(abs(x1-x2) + 1) { y1 }
        } else if (x1 == y1 && x2 == y2) {
            min(x1,x2) .. max(x1, x2) zip  min(x1,x2) .. max(x1, x2)
        } else {

            mutableListOf()
        }


    }

    fun intersect(secondLine: Line): Set<Pair<Int, Int>> {
        return this.getPoints().intersect(secondLine.getPoints().toSet())
    }
}

fun parseLine(line: String) : Line {
    val substringBeforeArrow = line.substringBefore("->").trim()
    val substringAfterArrow = line.substringAfter("->").trim()

    val x1 = substringBeforeArrow.substringBefore(',').toInt()
    val y1 = substringBeforeArrow.substringAfter(',').toInt()

    val x2 = substringAfterArrow.substringBefore(',').toInt()
    val y2 = substringAfterArrow.substringAfter(',').toInt()

    return Line(x1, y1, x2, y2)
}

fun main() {
    fun part1(input: List<String>): Int {

        val allLines = input.map { parseLine(it) }
        val filteredLines = allLines.filter { it.x1 == it.x2 || it.y1 == it.y2 }

        val commonPoints = mutableSetOf<Pair<Int,Int>>()

        for (index in 0.. filteredLines.size - 2) {

            for (innerIndex in index+1 until filteredLines.size) {
                commonPoints.addAll(filteredLines[index].intersect(filteredLines[innerIndex]))
            }
        }

        return commonPoints.size
    }

    fun part2(input: List<String>): Int {
        val allLines = input.map { parseLine(it) }
        val filteredLines = allLines

        println( Line(6, 4, 2, 0).getPoints() )


//        val filteredLines = allLines.filter {
////            (it.x1 == it.x2 && it.y1 != it.y2) || (it.y1 == it.y2 && it.x1 != it.x2) || (it.x1 == it.y1 && it.x2 == it.y2) || (it.x1 == it.y2 && it.x2 == it.y1)
//        }

//        println (filteredLines)

        val commonPoints = mutableSetOf<Pair<Int,Int>>()

        for (index in 0.. filteredLines.size - 2) {

            for (innerIndex in index+1 until filteredLines.size) {
                commonPoints.addAll(filteredLines[index].intersect(filteredLines[innerIndex]))
            }
        }

//        commonPoints.forEach { point -> println(point) }

        return commonPoints.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    val testInputPart1 = readInput("Day05")
    check(part1(testInput) == 5)
    check(part1(testInputPart1) == 4655)
    check(part2(testInput) == 12)

//    val input = readInput("Day05")
//    println(part1(input))
//    println(part2(input))
}