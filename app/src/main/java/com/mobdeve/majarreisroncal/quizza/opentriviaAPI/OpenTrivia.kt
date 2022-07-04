package com.mobdeve.majarreisroncal.quizza.opentriviaAPI

import android.text.Html
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

/* This class is for the connection and retrieval of questions
   from the database, and the generation of a unique token,
   which is required by the database to generate trivia questions
   to our specifications. */

class OpenTrivia{
    private val openTriviaURL = "https://opentdb.com/api.php"
    private val okHttpClient : OkHttpClient = OkHttpClient()
    private val category = 0

    fun questions(diff : GameDifficulty, token : String?) : GameAnswers {
        val  url = "$openTriviaURL?amount=1&category=$category&difficulty=$diff&type=multiple&token=$token";

        val req = Request.Builder().url(url).build()
        val res = okHttpClient.newCall(req).execute()
        val resStr: String?  = res.body()?.string()

        val resJson = JSONObject(resStr)
        val respCode = resJson.getInt("response_code")
        if (respCode != 0)
            throw  Exception("Unexpected response code: $respCode")

        val ansRes = resJson.getJSONArray("results").getJSONObject(0)
        var getAnswers = GameAnswers(
            Html.fromHtml(ansRes.getString("category")).toString(),
            Html.fromHtml(ansRes.getString("question")).toString(),
            4)
        getAnswers.correctAns = Html.fromHtml(ansRes.getString("correct_answer")).toString()

        var wrongAnswers = ansRes.getJSONArray("incorrect_answers")
        for (i in 0 until wrongAnswers.length()) {
            getAnswers.wrongAns.add(Html.fromHtml(wrongAnswers.getString(i)).toString())
        }
    return getAnswers
    }

    fun getToken(): String {
        val url = "https://opentdb.com/api_token.php?command=request"
        val request = Request.Builder().url(url).build()
        val response = okHttpClient.newCall(request).execute()
        val responseStr = response.body()?.string()
        val respBodyJson = JSONObject(responseStr)

        val respCode = respBodyJson.getInt("response_code")
        if (respCode != 0)
            throw  Exception("Unexpected response code: $respCode")
        return respBodyJson.getString("token")
    }



}
