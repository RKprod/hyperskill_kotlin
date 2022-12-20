fun main() {
    val str = mutableListOf(' ', ' ', ' ',' ', ' ', ' ',' ', ' ', ' ')
    val list = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' ')
    )

    fun showGrid() {
        println("---------")
        for (i in 0..2){
            println("| ${list[i].joinToString(" ")} |")
        }
        println("---------")    
    }

    showGrid()
    
    // fun countOccurrences(s: String, ch: Char): Int {
    //     return s.filter { it == ch }.count()
    // }
    val winCombo = mutableListOf(
        mutableListOf(0, 1, 2),
        mutableListOf(3, 4, 5),
        mutableListOf(6, 7, 8),
        mutableListOf(0, 3, 6),
        mutableListOf(1, 4, 7),
        mutableListOf(2, 5, 8),
        mutableListOf(0, 4, 8),
        mutableListOf(2, 4, 6)
    )

    fun checkWin(s: Char): Boolean {
        for (i in 0 until winCombo.size){
            if (s == str[winCombo[i][0]] && s == str[winCombo[i][1]] && s == str[winCombo[i][2]]) return true
        }
        return false
    }
    
    /*println(
        if (countOccurrences(str, 'X') - countOccurrences(str, 'O') !in -1..1) "Impossible"
        else if (checkWin('X') && checkWin('O')) "Impossible"
        else if (checkWin('X')) "X wins"
        else if (checkWin('O')) "O wins"
        else if ("_" in str) "Game not finished"
        else "Draw"
    )*/

    var iteration = 0 
    while (iteration >= 0) {
        var position = readln().split(" ")
        if (position.size != 2) {
            println("You should enter numbers!")
        } else if (!position[0].get(0).isDigit() || !position[1].get(0).isDigit()) {
            println("You should enter numbers!")
        } else if (position[0].toInt() > 3 || position[1].toInt() > 3) {
            println("Coordinates should be from 1 to 3!")
        } else if (list[position[0].toInt() - 1][position[1].toInt() - 1] != ' ') {
            println("This cell is occupied! Choose another one!")    
        } else {
            if (iteration % 2 == 0) {
                list[position[0].toInt() - 1][position[1].toInt() - 1] = 'X'
                str[(position[0].toInt()-1) * 3 + position[1].toInt() - 1] = 'X'
                iteration++
            } else {
                list[position[0].toInt() - 1][position[1].toInt() - 1] = 'O'
                str[(position[0].toInt()-1) * 3 + position[1].toInt() - 1] = 'O'
                iteration++
            }
            showGrid()
            /*if (countOccurrences(str, 'X') - countOccurrences(str, 'O') !in -1..1) {
                println("Impossible")
                iteration = -1
            }
            else */ if (checkWin('X') && checkWin('O')) {
                println("Impossible")
                iteration = -1
            }
            else if (checkWin('X')) {
                println("X wins")
                iteration = -1
            }
            else if (checkWin('O')) {
                println("O wins")
                iteration = -1
            }
            // else if ("_" in str) {
            //     println("Game not finished")
            // }
            else if (' ' !in str) {
                println("Draw")
                iteration = -1
            }
        }
    }
}
