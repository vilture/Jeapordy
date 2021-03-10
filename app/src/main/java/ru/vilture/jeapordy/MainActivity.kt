package ru.vilture.jeapordy

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var round = 1
    var init = false
    var catQ = ""
    var catS: MediaPlayer? = null

    lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!init) {
            init = true
            setTimerF(600000)
            firstRound()
            getButtonQ()
        }

        nextBtn.setOnLongClickListener {
            if (round != 4)
                nextRound()

            return@setOnLongClickListener true
        }

        catS = MediaPlayer.create(
            this,
            R.raw.si_cat
        )

        CatScreen.setOnClickListener {
            CatScreen.visibility = View.GONE
            when (catQ) {
                "q1_2_400" -> Question(getString(R.string.q1_2_400), this).load()
                "q1_4_200" -> Question(getString(R.string.q1_4_200), this).load()
                "q2_3_300" -> Question(getString(R.string.q2_3_300), this).load()
                "q2_1_500" -> Question(getString(R.string.q2_1_500), this).load()
                "q3_4_500" -> Question(getString(R.string.q3_4_500), this).load()
                "q3_5_500)" -> Question(getString(R.string.q3_5_500), this).load()
            }
            catQ = ""
        }
    }

    private fun nextRound() {
        timer.cancel()

        if (round != 4) {
            round += 1
            setTimerF(600000)
            setVisibleQ()
        }

        when (round) {
            1 -> firstRound()
            2 -> twoRound()
            3 -> threeRound()
            4 -> fourRound()
        }

        getButtonQ()
    }

    private fun setTimerF(sec: Int) {

        timer = object : CountDownTimer(sec.toLong(), 1000) {
            //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                finishTimer.text =
                    "Раунд завершится через: " + millisUntilFinished / 1000 + " сек."
            }

            override fun onFinish() {
                finishTimer.text = "РАУНД ЗАВЕРШЕН!"
                val sound = MediaPlayer.create(
                    this@MainActivity,
                    resources.getIdentifier("si_roundend", "raw", packageName)
                )
                sound!!.start()
            }
        }.start()
    }

    private fun firstRound() {
        q1.text = getString(R.string.t1_1)
        q2.text = getString(R.string.t1_2)
        q3.text = getString(R.string.t1_3)
        q4.text = getString(R.string.t1_4)
        q5.text = getString(R.string.t1_5)
    }

    private fun twoRound() {
        q1.text = getString(R.string.t2_1)
        q2.text = getString(R.string.t2_2)
        q3.text = getString(R.string.t2_3)
        q4.text = getString(R.string.t2_4)
        q5.text = getString(R.string.t2_5)

    }

    private fun threeRound() {
        q1.text = getString(R.string.t3_1)
        q2.text = getString(R.string.t3_2)
        q3.text = getString(R.string.t3_3)
        q4.text = getString(R.string.t3_4)
        q5.text = getString(R.string.t3_5)
        nextBtn.text = "Финальный раунд"
    }

    private fun fourRound() {
        timer.cancel()
        finishTimer.text = ""
        nextBtn.visibility = View.GONE

        val intent = Intent(this, FinalActivity::class.java)
        startActivity(intent)
    }


    private fun getButtonQ() {
        /**
         * первая тема
         */

        /**
        Новый год
        Зимние виды спорта
        Полярники
         */
        q1v100.setOnClickListener {
            when (round) {
                // Старый новый год
                1 -> Question(getString(R.string.q1_1_100), this).load()
                // Хоккей
                2 -> Question(getString(R.string.q2_1_100), this).load()
                // Малый ковш
                3 -> Question(getString(R.string.q3_1_100), this).load()
            }

            q1v100.visibility = View.INVISIBLE
        }

        q1v200.setOnClickListener {
            when (round) {
                // Германия
                1 -> Question(getString(R.string.q1_1_200), this).load()
                // Кёрлинг
                2 -> Question(getString(R.string.q2_1_200), this).load()
                // газ
                3 -> Question(getString(R.string.q3_1_200), this).load()
            }

            q1v200.visibility = View.INVISIBLE
        }


        q1v300.setOnClickListener {
            when (round) {
                // дед мороз
                1 -> Question(getString(R.string.q1_1_300), this).load()
                // Восстанавливает поверхность льда
                2 -> Question(getString(R.string.q2_1_300), this).load()
                // Ленин
                3 -> Question(getString(R.string.q3_1_300), this).load()
            }

            q1v300.visibility = View.INVISIBLE
        }

        q1v400.setOnClickListener {
            when (round) {
                // сентябрь
                1 -> Question(getString(R.string.q1_1_400), this).load()
                // слалом
                2 -> Question(getString(R.string.q2_1_400), this).load()
                // Баренцево
                3 -> Question(getString(R.string.q3_1_400), this).load()
            }

            q1v400.visibility = View.INVISIBLE
        }

        q1v500.setOnClickListener {
            when (round) {
                // поземка
                1 -> Question(getString(R.string.q1_1_500), this).load()
                // брумбол
                2 -> {
                    catQ = "q2_1_500"
                    CatScreen.visibility = View.VISIBLE
                    catS!!.start()
                }
                // многолетний лед
                3 -> Question(getString(R.string.q3_1_500), this).load()
            }

            q1v500.visibility = View.INVISIBLE
        }

        /**
        вторая тема
         */

        /**
        Спокойной ночи
        Фамилии на букву "М"
        Праздники
         */
        q2v100.setOnClickListener {
            when (round) {
                //Томас Эдисон
                1 -> Question(getString(R.string.q1_2_100), this).load()
                // Андрей Миронов
                2 -> Question(getString(R.string.q2_2_100), this).load()
                // Зеленый
                3 -> Question(getString(R.string.q3_2_100), this).load()
            }

            q2v100.visibility = View.INVISIBLE
        }

        q2v200.setOnClickListener {
            when (round) {
                // сонник
                1 -> Question(getString(R.string.q1_2_200), this).load()
                // Александр Макдонский
                2 -> Question(getString(R.string.q2_2_200), this).load()
                // Туркменистан
                3 -> Question(getString(R.string.q3_2_200), this).load()
            }

            q2v200.visibility = View.INVISIBLE
        }

        q2v300.setOnClickListener {
            when (round) {
                // полярное сияние
                1 -> Question(getString(R.string.q1_2_300), this).load()
                // Карл маркс
                2 -> Question(getString(R.string.q2_2_300), this).load()
                // Праздник весны
                3 -> Question(getString(R.string.q3_2_300), this).load()
            }

            q2v300.visibility = View.INVISIBLE
        }

        q2v400.setOnClickListener {
            when (round) {
                // кровать
                1 -> {
                    catQ = "q1_2_400"
                    CatScreen.visibility = View.VISIBLE
                    catS!!.start()
                }
                // Вольф Мессинг
                2 -> Question(getString(R.string.q2_2_400), this).load()
                // День святого валентина
                3 -> Question(getString(R.string.q3_2_400), this).load()
            }

            q2v400.visibility = View.INVISIBLE
        }

        q2v500.setOnClickListener {
            when (round) {
                // Валентина Дворянинова
                1 -> Question(getString(R.string.q1_2_500), this).load()
                // Мэрилин Монро
                2 -> Question(getString(R.string.q2_2_500), this).load()
                // Каганер
                3 -> Question(getString(R.string.q3_2_500), this).load()
            }

            q2v500.visibility = View.INVISIBLE
        }

        /**
        третья тема
         */

        /**
        География
        Россия
        Животный мир
         */
        q3v100.setOnClickListener {
            when (round) {
                // Брюссель
                1 -> Question(getString(R.string.q1_3_100), this).load()
                // Ленинград
                2 -> Question(getString(R.string.q2_3_100), this).load()
                // белёк
                3 -> Question(getString(R.string.q3_3_100), this).load()
            }

            q3v100.visibility = View.INVISIBLE
        }

        q3v200.setOnClickListener {
            when (round) {
                // Монако
                1 -> Question(getString(R.string.q1_3_200), this).load()
                //Якутия
                2 -> Question(getString(R.string.q2_3_200), this).load()
                // птица
                3 -> Question(getString(R.string.q3_3_200), this).load()
            }

            q3v200.visibility = View.INVISIBLE
        }

        q3v300.setOnClickListener {
            when (round) {
                // Италия
                1 -> Question(getString(R.string.q1_3_300), this).load()
                // московский Кремль
                2 -> {
                    catQ = "q2_3_300"
                    CatScreen.visibility = View.VISIBLE
                    catS!!.start()
                }
                // телескоп
                3 -> Question(getString(R.string.q3_3_300), this).load()
            }

            q3v300.visibility = View.INVISIBLE
        }

        q3v400.setOnClickListener {
            when (round) {
                // Дарваз
                1 -> Question(getString(R.string.q1_3_400), this).load()
                // Челябинск
                2 -> Question(getString(R.string.q2_3_400), this).load()
                // США
                3 -> Question(getString(R.string.q3_3_400), this).load()
            }

            q3v400.visibility = View.INVISIBLE
        }

        q3v500.setOnClickListener {
            when (round) {
                //Северный Ледовитый
                1 -> Question(getString(R.string.q1_3_500), this).load()
                // Певек
                2 -> Question(getString(R.string.q2_3_500), this).load()
                // Новая Гвинея
                3 -> Question(getString(R.string.q3_3_500), this).load()
            }

            q3v500.visibility = View.INVISIBLE
        }

        /**
        четвертая тема
         */

        /**
         * Мультфильмы
         * Фильмы
         * Литература
         */
        q4v100.setOnClickListener {
            when (round) {
                // хвост
                1 -> Question(getString(R.string.q1_4_100), this).load()
                // Служебный роман
                2 -> Question(getString(R.string.q2_4_100), this).load()
                // манная
                3 -> Question(
                    getString(R.string.q3_4_100),
                    this
                ).load()
            }

            q4v100.visibility = View.INVISIBLE
        }

        q4v200.setOnClickListener {
            when (round) {
                // свадьба
                1 -> {
                    catQ = "q1_4_200"
                    CatScreen.visibility = View.VISIBLE
                    catS!!.start()
                }
                // ку
                2 -> Question(getString(R.string.q2_4_200), this).load()
                // каштанка
                3 -> Question(getString(R.string.q3_4_200), this).load()
            }

            q4v200.visibility = View.INVISIBLE
        }

        q4v300.setOnClickListener {
            when (round) {
                // сурикат
                1 -> Question(getString(R.string.q1_4_300), this).load()
                // Горбунков
                2 -> Question(getString(R.string.q2_4_300), this).load()
                // Швейк
                3 -> Question(getString(R.string.q3_4_300), this).load()
            }

            q4v300.visibility = View.INVISIBLE
        }

        q4v400.setOnClickListener {
            when (round) {
                // рюкзак
                1 -> Question(getString(R.string.q1_4_400), this).load()
                // канны
                2 -> Question(getString(R.string.q2_4_400), this).load()
                // Марк Твен
                3 -> Question(getString(R.string.q3_4_400), this).load()
            }

            q4v400.visibility = View.INVISIBLE
        }

        q4v500.setOnClickListener {
            when (round) {
                // килька
                1 -> Question(getString(R.string.q1_4_500), this).load()
                // Элефант
                2 -> Question(getString(R.string.q2_4_500), this).load()
                // Лысые Горы
                3 -> {
                    catQ = "q3_4_500"
                    CatScreen.visibility = View.VISIBLE
                    catS!!.start()
                }
            }

            q4v500.visibility = View.INVISIBLE
        }

        /**
        пятая тема
         */

        /**
         * Автомобили
         * Кухни мира
         * В былые времена
         */
        q5v100.setOnClickListener {
            when (round) {
                // чайка
                1 -> Question(getString(R.string.q1_5_100), this).load()
                // сало
                2 -> Question(getString(R.string.q2_5_100), this).load()
                // Екатеринбург
                3 -> Question(getString(R.string.q3_5_100), this).load()
            }

            q5v100.visibility = View.INVISIBLE
        }

        q5v200.setOnClickListener {
            when (round) {
                // полный
                1 -> Question(getString(R.string.q1_5_200), this).load()
                // Австрийской
                2 -> Question(getString(R.string.q2_5_200), this).load()
                // М. С. Горбачев
                3 -> Question(getString(R.string.q3_5_200), this).load()
            }

            q5v200.visibility = View.INVISIBLE
        }

        q5v300.setOnClickListener {
            when (round) {
                // Венгрии
                1 -> Question(getString(R.string.q1_5_300), this).load()
                // Украина
                2 -> Question(getString(R.string.q2_5_300), this).load()
                // тетрис
                3 -> Question(getString(R.string.q3_5_300), this).load()
            }

            q5v300.visibility = View.INVISIBLE
        }

        q5v400.setOnClickListener {
            when (round) {
                // Фритта
                1 -> Question(getString(R.string.q1_5_400), this).load()
                // Ольвье
                2 -> Question(getString(R.string.q2_5_400), this).load()
                // 100гр для храбрости
                3 -> Question(getString(R.string.q3_5_400), this).load()
            }

            q5v400.visibility = View.INVISIBLE
        }

        q5v500.setOnClickListener {
            when (round) {
                // Миасс
                1 -> Question(getString(R.string.q1_5_500), this).load()
                // телятина
                2 -> Question(getString(R.string.q2_5_500), this).load()
                // Пищепром
                3 -> {
                    catQ = "q3_5_500)"
                    CatScreen.visibility = View.VISIBLE
                    catS!!.start()
                }
            }

            q5v500.visibility = View.INVISIBLE
        }
    }

    private fun setVisibleQ() {
        q1v100.visibility = View.VISIBLE
        q1v200.visibility = View.VISIBLE
        q1v300.visibility = View.VISIBLE
        q1v400.visibility = View.VISIBLE
        q1v500.visibility = View.VISIBLE
        q2v100.visibility = View.VISIBLE
        q2v200.visibility = View.VISIBLE
        q2v300.visibility = View.VISIBLE
        q2v400.visibility = View.VISIBLE
        q2v500.visibility = View.VISIBLE
        q3v100.visibility = View.VISIBLE
        q3v200.visibility = View.VISIBLE
        q3v300.visibility = View.VISIBLE
        q3v400.visibility = View.VISIBLE
        q3v500.visibility = View.VISIBLE
        q4v100.visibility = View.VISIBLE
        q4v200.visibility = View.VISIBLE
        q4v300.visibility = View.VISIBLE
        q4v400.visibility = View.VISIBLE
        q4v500.visibility = View.VISIBLE
        q5v100.visibility = View.VISIBLE
        q5v200.visibility = View.VISIBLE
        q5v300.visibility = View.VISIBLE
        q5v400.visibility = View.VISIBLE
        q5v500.visibility = View.VISIBLE
    }

    override fun onBackPressed() {}

}