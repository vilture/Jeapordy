package ru.vilture.jeapordy

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_final.*
import kotlinx.android.synthetic.main.activity_final.finishTimer
import kotlinx.android.synthetic.main.activity_main.*

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

        ok.setOnClickListener {
            if (winner.isVisible)
                startWinner()
            else
                if (finishTimer.text == "")
                    startTimer()
        }
    }

    private fun getFinalQ(btn: Button) {

        if (question.size == 1) {
            tx_question.text = question.entries.iterator().next().value
            tx_question.visibility = View.VISIBLE

            btn.isClickable = false
            ok.visibility = View.VISIBLE
        } else {
            btn.visibility = View.GONE
        }

    }

    private fun startTimer() {
        val sound = MediaPlayer.create(this@FinalActivity, R.raw.si_30sec)
        sound.start()

        object : CountDownTimer(61000.toLong(), 1000) {
            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                finishTimer.text =
                    "Раунд завершится через: " + millisUntilFinished / 1000 + " сек."
            }

            override fun onFinish() {
                finishTimer.text = "Финалисты,ваши ответы"
                winner.visibility = View.VISIBLE
                ok.text = "Победитель"
            }
        }.start()
    }

    private fun setQ() {
        // степлер
        question["1"] = getString(R.string.finalQ1)

        // Таиланд
        question["2"] = getString(R.string.finalQ2)

        // Агата Кристи (Ага́та Мэ́ри Клари́сса)
        question["3"] = getString(R.string.finalQ3)

        // Boston Dynamics
        question["4"] = getString(R.string.finalQ4)

        // Джекфрут
        question["5"] = getString(R.string.finalQ5)

        // Киану Ривз
        question["6"] = getString(R.string.finalQ6)


        // Коко Шанель
        question["7"] = getString(R.string.finalQ7)
    }

    private fun startWinner() {
        WinnerScreen.visibility = View.VISIBLE
        winner_tx.text = winner.text
        val sound = MediaPlayer.create(this@FinalActivity, R.raw.si_finaltit)
        sound.start()
    }

    override fun onBackPressed() {
        if (WinnerScreen.visibility == View.VISIBLE) {
            finish()
            val intent = Intent(this@FinalActivity, StartActivity::class.java)
            startActivity(intent)
        }
    }


}