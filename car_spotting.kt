fun main() {
    var slot: MutableList<String> = mutableListOf()
    var ins = readln().split(" ")

    while (ins[0] != "exit"){
        if (ins[0] == "create"){
            slot = mutableListOf()
            for (i in 1..ins[1].toInt()) slot.add("")
            println("Created a parking lot with ${ins[1]} spots.")
        } else if (ins[0] == "leave") {
            if (slot.size == 0) println("Sorry, a parking lot has not been created.") 
            else {
                slot[ins[1].toInt() - 1] = ""
                println("Spot ${ins[1]} is free.")
            }
        } else if (ins[0] == "park"){
            if (slot.size == 0) println("Sorry, a parking lot has not been created.") 
            else {
                var empty = slot.indexOf("")
                if (empty == -1) println("Sorry, the parking lot is full.")
                else {
                    slot[empty] = "${empty+1} ${ins[1]} ${ins[2]}"
                    println("${ins[2]} car parked in spot ${empty+1}.")
                }
            }
        } else if (ins[0] == "status"){
            if (slot.size == 0) println("Sorry, a parking lot has not been created.") 
            else {
                var flag = true
                for (i in 0..slot.size-1){
                    if (slot[i] != ""){ 
                        println(slot[i])
                        flag = false
                    }
                }
                if (flag) println("Parking lot is empty.")
            }
        } else if (ins[0] == "spot_by_color"){
            if (slot.size == 0) println("Sorry, a parking lot has not been created.") 
            else {
                var spotList: MutableList<Int> = mutableListOf()
                for (i1 in 0..slot.size-1){
                    if (slot[i1] != ""){
                        if (slot[i1].split(" ")[2].lowercase() == ins[1].lowercase()) spotList.add(i1 + 1)
                    }
                }
                if (spotList.size == 0) println("No cars with color ${ins[1]} were found.")
                else println(spotList.joinToString(", "))
            }
        } else if (ins[0] == "spot_by_reg"){
            if (slot.size == 0) println("Sorry, a parking lot has not been created.") 
            else {
                var flag1 = true
                for (i2 in 0..slot.size-1){
                    if (slot[i2] != ""){
                        if (slot[i2].split(" ")[1] == ins[1]) {
                            println(i2 + 1)
                            flag1 = false
                        }
                    }
                }
                if (flag1) println("No cars with registration number ${ins[1]} were found.")
            }
        } else if (ins[0] == "reg_by_color"){
            if (slot.size == 0) println("Sorry, a parking lot has not been created.") 
            else {
                var spotList2: MutableList<String> = mutableListOf()
                for (i3 in 0..slot.size-1){
                    if (slot[i3] != ""){
                        if (slot[i3].split(" ")[2].lowercase() == ins[1].lowercase()) spotList2.add(slot[i3].split(" ")[1])
                    }
                }
                if (spotList2.size == 0) println("No cars with color ${ins[1]} were found.")
                else println(spotList2.joinToString(", "))
            }
        }
        ins = readln().split(" ")
    }
}
