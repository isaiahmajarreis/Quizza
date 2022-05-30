package com.mobdeve.majarreisroncal.quizza.opentriviaAPI

class GameAnswers constructor(val category : String, val question: String, val nAnswers: Int)
{
    var wrongAns : ArrayList<String> = ArrayList(nAnswers)
    var correctAns : String = ""
    init {

    }
}