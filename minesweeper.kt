import kotlin.random.Random

fun main() {
    print("How many mines do you want on the field? ")
    val num = readln().toInt()
    val minePos: MutableList<Int> = mutableListOf()
    val selectedPos: MutableList<Int> = mutableListOf()
    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    
    var displayGame = mutableListOf(
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
        mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", ".")
    ) 

    fun printGame(){
        println(" │123456789│")
        println("—│—————————│")
        for (i in 0..8) {
            print("${i+1}|")
            for (j in 0..8) {
                // if (displayGame[i][j] == "X") print(".")
                // else print(game[i][j])
                print(displayGame[i][j])
            }
            println("|")
        }  
        println("—│—————————│")
    }

    printGame()

    print("Set/unset mines marks or claim a cell as free: ")
    var game: MutableList<MutableList<String>> = mutableListOf()
    var position = readln().split(" ")
    var a = 0
    while (a < num){
        var temp = Random.nextInt(0, 80)
        if (temp !in minePos && temp != (position[1].toInt() - 1) * 9 + position[0].toInt() -1){
            minePos.add(temp)
            a++
        }  
    }
    var row: MutableList<String> = mutableListOf()
    for (k in 0..80) {
        if (k in minePos) row.add("X")
        else row.add(".")
        if ((k + 1) % 9 == 0) {
            game.add(row)
            row = mutableListOf()
        }
    }
    
    for (i in 0..8) {
        var value = 0
        for (j in 0..8) {
            if (game[i][j] != "X"){
                if (i != 0 && j!= 0) {
                    if (game[i-1][j-1] == "X") value++
                }
                if (i != 0) {
                    if (game[i-1][j] == "X") value++
                }
                if (i != 0 && j != 8) {
                    if (game[i-1][j+1] == "X") value++
                }
                if (j != 0) {
                     if (game[i][j-1] == "X") value++
                }
                if (j != 8) {
                    if (game[i][j+1] == "X") value++
                }
                if (i != 8 && j != 0) {
                    if (game[i+1][j-1] == "X") value++
                }
                if (i != 8) {
                    if (game[i+1][j] == "X") value++
                }
                if (i != 8 && j!= 8) {
                    if (game[i+1][j+1] == "X") value++
                }
                if (value > 0) {
                    game[i][j] = value.toString()
                    value = 0
                }    
            }     
        }
    }

    fun makeFree(x: Int, y: Int) {
        if (game[x][y] != "X"){
            if (game[x][y] == "."){
                displayGame[x][y] = "/"
                game[x][y] = "/"
            } else displayGame[x][y] = game[x][y]            
            if (x != 0 && y!= 0) {
                if (game[x-1][y-1] == ".") makeFree(x-1,y-1)
                else if (game[x-1][y-1].matches(regex)) displayGame[x-1][y-1] = game[x-1][y-1]
            }
            if (x != 0) {
                if (game[x-1][y] == ".") makeFree(x-1,y)
                else if (game[x-1][y].matches(regex)) displayGame[x-1][y] = game[x-1][y]
            }
            if (x != 0 && y != 8) {
                if (game[x-1][y+1] == ".") makeFree(x-1,y+1)
                else if (game[x-1][y+1].matches(regex)) displayGame[x-1][y+1] = game[x-1][y+1]
            }
            if (y != 0) {
                if (game[x][y-1] == ".") makeFree(x,y-1)
                else if (game[x][y-1].matches(regex)) displayGame[x][y-1] = game[x][y-1]
            }
            if (y != 8) {
                if (game[x][y+1] == ".") makeFree(x,y+1)
                else if (game[x][y+1].matches(regex)) displayGame[x][y+1] = game[x][y+1]
            }
            if (x != 8 && y != 0) {
                if (game[x+1][y-1] == ".") makeFree(x+1,y-1)
                else if (game[x+1][y-1].matches(regex)) displayGame[x+1][y-1] = game[x+1][y-1]
            }
            if (x != 8) {
                if (game[x+1][y] == ".") makeFree(x+1,y)
                else if (game[x+1][y].matches(regex)) displayGame[x+1][y] = game[x+1][y]
            }
            if (x != 8 && y!= 8) {
                if (game[x+1][y+1] == ".") makeFree(x+1,y+1)
                else if (game[x+1][y+1].matches(regex)) displayGame[x+1][y+1] = game[x+1][y+1]
            }        
        }
    }

    if (position[2] == "free") makeFree(position[1].toInt() - 1, position[0].toInt() -1)
    else displayGame[position[1].toInt()-1][position[0].toInt()-1] = "*"
    printGame()

    var flag = true
    while(!selectedPos.containsAll(minePos) || !minePos.containsAll(selectedPos)){
        print("Set/unset mines marks or claim a cell as free: ")
        position = readln().split(" ")
        if (position[2] == "mine"){
            // if (displayGame[position[1].toInt()-1][position[0].toInt()-1].matches(regex)) println("There is a number here!")
            // else if (displayGame[position[1].toInt()-1][position[0].toInt()-1] != "/") {
                if (displayGame[position[1].toInt()-1][position[0].toInt()-1] == "*") {
                    displayGame[position[1].toInt()-1][position[0].toInt()-1] = "."
                    selectedPos.remove((position[1].toInt() - 1) * 9 + position[0].toInt() -1)
                }
                else {
                    displayGame[position[1].toInt()-1][position[0].toInt()-1] = "*"
                    selectedPos.add((position[1].toInt() - 1) * 9 + position[0].toInt() -1)
                }
            // }
        } else if (position[2] == "free"){
            if (game[position[1].toInt() -1][position[0].toInt() -1] == "X") {
                println("You stepped on a mine and failed!")
                flag = false
                break
            }else {
                makeFree(position[1].toInt() - 1, position[0].toInt() -1)
            }
        }
        printGame()

    }
    if (flag) println("Congratulations! You found all the mines!")
}
