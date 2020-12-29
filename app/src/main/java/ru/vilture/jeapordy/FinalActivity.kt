package ru.vilture.jeapordy

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_final.*
import kotlinx.android.synthetic.main.activity_final.finishTimer

class FinalActivity : AppCompatActivity() {

    private val question = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        setQ()


        finalQ1.setOnClickListener {
            getFinalQ(finalQ1)
            question.remove("1")
        }

        finalQ2.setOnClickListener {
            getFinalQ(finalQ2)
            question.remove("2")
        }

        finalQ3.setOnClickListener {
            getFinalQ(finalQ3)
            question.remove("3")
        }

        finalQ4.setOnClickListener {
            getFinalQ(finalQ4)
            question.remove("4")
        }

        finalQ5.setOnClickListener {
            getFinalQ(finalQ5)
            question.remove("5")
        }

        finalQ6.setOnClickListener {
            getFinalQ(finalQ6)
            question.remove("6")
        }

        finalQ7.setOnClickListener {
            getFinalQ(finalQ7)
            question.remove("7")
        }

    }

    private fun getFinalQ(btn: Button){

        if (question.size == 1) {
            tx_question.text = question.entries.iterator().next().value
            tx_question.visibility = View.VISIBLE

            btn.isClickable = false

            startTimer()
        } else {
            btn.visibility = View.GONE
        }

    }

    private fun startTimer() {


        object : CountDownTimer(47000.toLong(), 1000) {
            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                finishTimer.text =
                    "Раунд завершится через: " + millisUntilFinished / 1000 + " сек."

                if (millisUntilFinished in 32000 downTo 31000.toLong()){
                    val sound = MediaPlayer.create(this@FinalActivity, R.raw.si_30sec)
                    sound.start()
                }
            }

            override fun onFinish() {
                finishTimer.text = "Финалисты,ваши ответы"
            }
        }.start()
    }

    private fun setQ() {
        // степлер
        question["1"] = "Король Луи XV был первым человеком, использовавшим Это." +
                " Предмет был сделан из чистого золота и инкрустирован драгоценными камнями," +
                " а каждое комплектующее имело знак отличия"

        // Беларусь
        question["2"] =
            "Интересные факты о этой стране - первая кто легализовала криптовалюты, \n" +
                    "одна из самых грамотный стран занимающее 8 место ЮНЕСКО, \n" +
                    "имеет два гос.языка, но один из них номинальный"

        question["3"] = ""
        question["4"] = ""
        question["5"] = ""
        question["6"] = ""
        question["7"] = ""
    }
}