package com.daviiid99.sum_petit


import kotlin.random.Random

class Logic(val status : Boolean = true) {

    var currentOperation : MutableList<Int> = mutableListOf()
    val currentSymbols : MutableList<String> = mutableListOf("+", "-", "*", "/")
    var randomResults : MutableList<Int> = mutableListOf()
    var random : Int = Random.nextInt(1,10)
    var currentResult : Int = 0
    var currentSymbol : String = ""


    fun newOperation(){
        // A combination of two method

        // Get operators
        getRandomOperators()

        // Get symbol
        getRandomSymbol()

        // Perform operation
        performOperation()

        // Get random results
        generateRandomResults()

    }


    fun getRandomOperators(){
        // A method to get random symbol

        if (currentOperation.size > 0){
            currentOperation.clear()
        }

        while(currentOperation.size < 2){
            var number : Int = Random.nextInt(1, 10)
            // Generate two random numbers and save them into a mutable list
            currentOperation.add(number)
        }

        print("Random operators ${currentOperation}")
    }

    fun getRandomSymbol(){
        // Return a random symbol to perform an operation
        var randomSymbol : Int = Random.nextInt(0, currentSymbols.size -1)
        currentSymbol = currentSymbols[randomSymbol]
    }

    fun getCurrentSymbolIndex() : Int{
        // Returns an index of current symbol
        return currentSymbols.indexOf(currentSymbol)
    }

    fun performOperation(){
        // Given the operators and symbol, perform the operation
        // Save result into a variable

        currentResult = when(currentSymbol){
            "+" -> currentOperation[0] + currentOperation[1]
            "-" -> currentOperation[0] - currentOperation[1]
            "*" -> currentOperation[0] * currentOperation[1]
            else -> {
                currentOperation[0] / currentOperation[1]
            }
        }

    }

    fun generateRandomResults(){
        // Generate two random results for the operation

        if(randomResults.size > 0){
            randomResults.clear()
        }

        while (randomResults.size < 2){
            var number : Int = Random.nextInt(1, 10)
            if (!randomResults.contains(number) && number != currentResult){
                // Check there's not an even result
                randomResults.add(number)
            }
        }

    }

    fun mixResults(){
        // In order to display all results, we need to mix all
        // Compare numbers and add them into a results list
        randomResults.add(currentResult)
        randomResults.shuffle()

    }

}