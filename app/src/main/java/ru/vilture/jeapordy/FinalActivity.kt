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

        object : CountDownTimer(31000.toLong(), 1000) {
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
        question["1"] = "Король Луи XV был первым человеком, использовавшим Это." +
                " Предмет был сделан из чистого золота и инкрустирован драгоценными камнями," +
                " а каждое комплектующее имело знак отличия"

        // Таиланд
        question["2"] =
            "Интересные факты о этой стране - \nсейчас идет 2564год, а новый год там празднукют аж 3 раза;\n" +
                    "В стране есть Вегетарианский фестиваль, но он отнюдь не про растительную пищу, " +
                    "а для демонстрации возможностей человеческого тела;\nВластями страны наложен запрет на вывоз " +
                    "национального «Короля фруктов», за что можно попасть в тюрьму "

        // Агата Кристи (Ага́та Мэ́ри Клари́сса)
        question["3"] =
            "Она работала медсестрой в период Первой мировой войны и разбиралась в ядах как никто другой, \n " +
                    "после войны собиралась стать фармацевтом, однако вместо профессии приобрела неискоренимую фобию — " +
                    "совершить ошибку в приготовлении и получить смертельный яд вместо лекарства. \n" +
                    "Впоследствии это оказало сильное влияния на ее жизненное творчество"

        // Boston Dynamics
        question["4"] =
            "Это одна из самых известных и раскрученных в мире компаний в области робототехники.\n" +
                    "Одним из первых заказов стало поручение разработать для нужд армии робота, который бы оказал помощь в доставке грузов там," +
                    " где не сможет проехать колесный или гусеничный транспорт.\n Этой компанией владели и Google, и SoftBank, " +
                    "а теперь ее заполучили Hyundai"

        // Джекфрут
        question["5"] = "Это древесное растение, вид рода Артокарпус семейства Тутовые.\n " +
                "Это вечнозелёное дерево, достигающее высоты 20 м. Листья темно-зелёные кожистые овальные, длиной до 22 см;\n " +
                "Его плоды — самые большие съедобные плоды, произрастающие на деревьях: " +
                "длиной 20—110 см и диаметром до 20 см, они весят до 34 кг. Родиной дерева считают Индию и Бангладеш"

        // Киану Ривз
        question["6"] = "Он за свою карьеру снялся более чем в 70 фильмах в самых разнообразных ролях. " +
                "Зрители настолько привыкли видеть его на экране, что редко задумываются, насколько длинна карьера актера, " +
                "которому уже исполнилось полвека.\n " +
                "Его имя  означает «прохладный ветер над горами» на гавайском языке.\n" +
                "Он играл на бас-гитаре в двух музыкальных группах, а еще он левша"


        // Коко Шанель
        question["7"] =
            "Кому принадлежит данная цитата:\n «Если вы хотите иметь то, что никогда не имели, " +
                    "вам придётся делать то, что никогда не делали.»\n" +
                    "Немного фактов о личности: " +
                    "Она жила при монастыре, была помощником продавца в магазине белья, " +
                    "а в свободное время пела в кабаре. " +
                    "Девушка мечтала стать балериной, певицей, танцовщицей, ходила по кастингам, но неудачно. " +
                    "Свое знаменитое прозвище она получила в кафе после исполнения песни"
    }

    private fun startWinner() {
        WinnerScreen.visibility = View.VISIBLE
        winner_tx.text = winner.text
        val sound = MediaPlayer.create(this@FinalActivity, R.raw.si_finaltit)
        sound.start()
    }

    override fun onBackPressed() {
        finish()
        val intent = Intent(this@FinalActivity, StartActivity::class.java)
        startActivity(intent)
    }


}