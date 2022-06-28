package com.mobdeve.majarreisroncal.quizza

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import com.mobdeve.majarreisroncal.quizza.databinding.ActivityPlayBinding
import com.mobdeve.majarreisroncal.quizza.opentriviaAPI.*
import java.util.*
import java.util.concurrent.Executors
import kotlin.math.floor

class PlayActivity : AppCompatActivity() {
    private var correctInd : Int = 0
    private var score : Int = 0
    private var difficulty : GameDifficulty = GameDifficulty.easy // default difficulty = easy
    private var token : String? = null

    private val time: Long = 15
    private val loadQuestionsExecutor = Executors.newSingleThreadExecutor()

    private lateinit var clickCorrect: MediaPlayer
    private lateinit var clickWrong: MediaPlayer
    private lateinit var binding: ActivityPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        difficulty = GameDifficulty.easy
        token = intent.getStringExtra(EXTRA_MESSAGE_TOKEN)
        supportActionBar?.hide()
        setContentView(binding.root)

        clickCorrect = MediaPlayer.create(this, R.raw.correct)
        clickWrong = MediaPlayer.create(this, R.raw.wrong)

        val mushroom = SlidingAnimation(binding.ivMushroom)
        val tomato = SlidingAnimation(binding.ivTomato)
        val bellPepper = SlidingAnimation(binding.ivBellPepper)
        val onion = SlidingAnimation(binding.ivOnion)
        val garlic = SlidingAnimation(binding.ivGarlic)
        val cheese = SlidingAnimation(binding.ivCheese)
        val pepperoni = SlidingAnimation(binding.ivPepperoni)

        mushroom.initializePosition()
        tomato.initializePosition()
        bellPepper.initializePosition()
        onion.initializePosition()
        garlic.initializePosition()
        cheese.initializePosition()
        pepperoni.initializePosition()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                Handler(Looper.getMainLooper()).post {
                    mushroom.move()
                    tomato.move()
                    bellPepper.move()
                    onion.move()
                    garlic.move()
                    cheese.move()
                    pepperoni.move()
                }
            }
        }, 0, 20)

        // TODO: fragments—one for countdown and one for actual game (maybe another for "time's up" screen)

        getQnA(this)

        Handler(Looper.getMainLooper()).postDelayed({
            val goToScore = Intent(this, ScoreActivity::class.java)
            goToScore.putExtra("score", score)
            startActivity(goToScore)
        }, time * 1000)

    }

    fun answerClicked(view : View)
    {
        if(view is RadioButton)
        {
            val isChecked = view.isChecked
            var checker = false

            when(view.id)
            {
                R.id.answer0 -> {
                    if(correctInd == 0)
                        checker = true
                    else
                        binding.answer0.setBackgroundResource(R.drawable.button_wrong)
                }

                R.id.answer1 -> {
                    if(correctInd == 1)
                        checker = true
                    else
                        binding.answer1.setBackgroundResource(R.drawable.button_wrong)
                }

                R.id.answer2 -> {
                    if(correctInd == 2)
                        checker = true
                    else
                        binding.answer2.setBackgroundResource(R.drawable.button_wrong)
                }

                R.id.answer3 -> {
                    if(correctInd == 3)
                        checker = true
                    else
                        binding.answer3.setBackgroundResource(R.drawable.button_wrong)
                }
            }

            when(correctInd)
            {
                0 -> binding.answer0.setBackgroundResource(R.drawable.button_green)
                1 -> binding.answer1.setBackgroundResource(R.drawable.button_green)
                2 -> binding.answer2.setBackgroundResource(R.drawable.button_green)
                3 -> binding.answer3.setBackgroundResource(R.drawable.button_green)
            }

            if(checker) {
                score++
                clickCorrect.start()
            }
            else
                clickWrong.start()

            binding.score.text = "$score"
        }

        getQnA(this)
        this.findViewById<RadioGroup>(R.id.radioGroup).isSelected = false

        Handler(Looper.getMainLooper()).postDelayed({
            resetBtn(binding.answer0)
            resetBtn(binding.answer1)
            resetBtn(binding.answer2)
            resetBtn(binding.answer3)
        }, 250)


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

    private fun resetBtn (radio : RadioButton)
    {
        radio.setBackgroundResource(R.drawable.button)
        radio.isChecked = false
        radio.isEnabled = true
    }

}

