package com.mobdeve.majarreisroncal.quizza.opentriviaAPI

/* used to store the retrieved answers from the openTrivia
   database a category, a question, and an integer indicating
   the number of possible answers there will be is passed */

class GameAnswers constructor(val category : String, val question: String, val nAnswers: Int)
{
    var wrongAns : ArrayList<String> = ArrayList(nAnswers)
    var correctAns : String = ""
    init {

    }
}