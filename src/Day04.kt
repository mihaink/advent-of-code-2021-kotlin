private class Board {
    var winningBingoNumber: Int = 0
    var rows : MutableList<List<Int>> = mutableListOf()
    var markedNumbers : MutableList<Int> = mutableListOf()

    fun addRow(row : List<Int>) {
        rows.add(row)
    }

    fun markNumber(number: Int) {
        if (rows.any { it.contains(number) }) {
            markedNumbers.add(number)
        }
    }

    fun checkBingo(): Boolean {
        for (row in rows) {
            if (markedNumbers.size >= 5 && markedNumbers.containsAll(row)) {
                return true
            }
        }

        for (index in 0 until 5) {
            if (  markedNumbers.size >= 5 && markedNumbers.containsAll(rows.map { it[index] }) ) {
                return true
            }
        }

        return false
    }
}

fun main() {
    fun part1(input: List<String>): Int {

        val bingoNumbers = input[0].split(',').map { it.toInt() }
        val boards : MutableList<Board> = mutableListOf()

        // This part is hideous right now, it fills in the numbers on the boards.
        var boardId = -1

        for (index in 1 until input.size) {
            if (input[index].isBlank()) {
                boards.add(Board())
                boardId++
            } else {
                boards[boardId].addRow( input[index].split(' ').filter { it.isNotBlank() }.map { it.toInt() } )
            }
        }

        // Start iterating over the Bingo numbers

        for (bingoNumber in bingoNumbers) {
            boards.forEach { board -> board.markNumber(bingoNumber) }
            val results = boards.filter { it.checkBingo() }

            if (results.isNotEmpty()) {
                val winningBoard = results.first()

                return winningBoard.rows.flatten().subtract(winningBoard.markedNumbers.toSet()).sum() * bingoNumber
            }
        }

        return -1
    }

    fun part2(input: List<String>): Int {
        val bingoNumbers = input[0].split(',').map { it.toInt() }

        val boards : MutableList<Board> = mutableListOf()

        // This part is hideous right now, it fills in the numbers on the boards.
        var boardId = -1

        for (index in 1 until input.size) {
            if (input[index].isBlank()) {
                boards.add(Board())
                boardId++
            } else {
                boards[boardId].addRow( input[index].split(' ').filter { it.isNotBlank() }.map { it.toInt() } )
            }
        }


        val winningBoards = mutableListOf<Board>()
        // Start iterating over the Bingo numbers

        for (bingoNumber in bingoNumbers) {

            boards.filter { !it.checkBingo() }.forEach { board -> board.markNumber(bingoNumber) }

            for(board in boards) {
                if (board.checkBingo()) {
                    if ( board !in winningBoards) {
                        board.winningBingoNumber = bingoNumber
                        winningBoards.add(board)
                    }
                }
            }
        }

        val lastWinnigBoard = winningBoards.last()

        return lastWinnigBoard.rows.flatten().subtract(lastWinnigBoard.markedNumbers.toSet()).sum() * lastWinnigBoard.winningBingoNumber

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}