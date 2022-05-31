package com.mobdeve.majarreisroncal.quizza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityPlayBinding
import com.mobdeve.majarreisroncal.quizza.opentriviaAPI.*
import java.util.concurrent.Executors
import kotlin.math.floor


class PlayActivity : AppCompatActivity() {
    private var correctInd : Int = 0
    private var score : Int = 0
    private var difficulty : GameDifficulty = GameDifficulty.easy
    // default difficulty = easy
    private var token : String? = null
    private val loadQuestionsExecutor = Executors.newSingleThreadExecutor()
    private lateinit var binding: ActivityPlayBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        difficulty = GameDifficulty.easy
        token = intent.getStringExtra(EXTRA_MESSAGE_TOKEN)
        supportActionBar?.hide()
        setContentView(binding.root)

        // TODO: fragments—one for countdown and one for actual game (maybe another for "time's up" screen)

        binding.question.text = ""
        binding.answer0.text = ""
        binding.answer1.text = ""
        binding.answer2.text = ""
        binding.answer3.text = ""

        getQnA(this)

/*
        Handler(Looper.getMainLooper()).postDelayed({
            val goToScore = Intent(this, ScoreActivity::class.java)
            goToScore.putExtra("score", score)
            startActivity(goToScore)
        }, 3000)

 */
    }

    fun answerClicked(view : View)
    {
        if(view is RadioButton)
        {
            val isChecked = view.isChecked
            var checker = false
            when(view.id)
            {
                R.id.answer0 -> if(correctInd == 0) checker = true
                R.id.answer1 -> if(correctInd == 1) checker = true
                R.id.answer2 -> if(correctInd == 2) checker = true
                R.id.answer3 -> if(correctInd == 3) checker = true
            }
            //changeBtn(binding.answer0, 0)
            //changeBtn(binding.answer1, 1)
            //changeBtn(binding.answer2, 2)
            //changeBtn(binding.answer3, 3)

            if (correctInd == 0)
                btnCorrect(binding.answer0)
            else if (correctInd == 1)
                btnCorrect(binding.answer1)
            else if (correctInd == 2)
                btnCorrect(binding.answer2)
            else if (correctInd == 3)
                btnCorrect(binding.answer3)
            else
            {
                if (correctInd != 0)
                    btnWrong(binding.answer0)
                if (correctInd != 1)
                    btnWrong(binding.answer1)
                if (correctInd != 2)
                    btnWrong(binding.answer2)
                if (correctInd != 3)
                    btnWrong(binding.answer3)
            }

            if(checker) { score++ }
                Toast.makeText(this, "Score: $score", Toast.LENGTH_LONG).show()
        }
        Handler(Looper.getMainLooper()).postDelayed({
        }, 500)

        getQnA(this)
        this.findViewById<RadioGroup>(R.id.radioGroup).isSelected = false
        resetBtn(binding.answer0)
        resetBtn(binding.answer1)
        resetBtn(binding.answer2)
        resetBtn(binding.answer3)

    }

    private fun getQnA(activity : PlayActivity)
    {
        loadQuestionsExecutor.execute()
        {
            try
            {
                val ans = OpenTrivia().questions(difficulty, token)

                activity.runOnUiThread {
                    activity.binding.question.text = ans.question

                    val correct = floor(Math.random() * 4).toInt()
                    var i = 0
                    activity.correctInd = correct

                    activity.binding.answer0.text = if (correct == 0) {
                        ans.correctAns
                    } else {
                        ans.wrongAns[i++]
                    }
                    activity.binding.answer1.text = if (correct == 1) {
                        ans.correctAns
                    } else {
                        ans.wrongAns[i++]
                    }
                    activity.binding.answer2.text = if (correct == 2) {
                        ans.correctAns
                    } else {
                        ans.wrongAns[i++]
                    }
                    activity.binding.answer3.text = if (correct == 3) {
                        ans.correctAns
                    } else {
                        ans.wrongAns[i++]
                    }

                }
            } catch (e: Exception) {}
        }

    }


    private fun changeBtn (radio : RadioButton, index : Int)
    {
        if(correctInd == index){
            radio.setBackgroundResource(R.drawable.button_green)
        }
        else{
           radio.setBackgroundResource(R.drawable.button_wrong)
        }
        radio.isEnabled = false
    }

    private fun btnCorrect (radio : RadioButton)
    {
        radio.setBackgroundResource(R.drawable.button_green)
        radio.isEnabled = false
    }

    private fun btnWrong (radio : RadioButton)
    {
        radio.setBackgroundResource(R.drawable.button_play)
        radio.isEnabled = false
    }

    private fun resetBtn (radio : RadioButton)
    {
        radio.setBackgroundResource(R.drawable.button)
        radio.isChecked = false
        radio.isEnabled = true
    }

}

