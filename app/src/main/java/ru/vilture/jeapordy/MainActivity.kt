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
            setTimerF(800000)
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
                "1q2v400" -> Question("Греческое клине, на русском языке именно это", this).load()
                "1q4v200" -> Question(
                    "На какой праздник пришёл волк к псу из мультфильма «Жил-был пёс»?",
                    this
                ).load()
                "2q3v300" -> Question("Самая большая в мире средневековая крепость", this).load()
                "2q1v500" -> Question(
                    "Игра напоминающая хоккей, но вместо коньков обычная обувь, а вместо клюшек – метла ",
                    this
                ).load()
                "3q4v500" -> Question(
                    "Как называлось имение Болконских в романе «Война и мир»?",
                    this
                ).load()
                "3q5v500" -> Question(
                    "Какое министерство отвечало в СССР за производство газированной воды?",
                    this
                ).load()
            }
            catQ = ""
        }
    }

    private fun nextRound() {
        timer.cancel()

        if (round != 4) {
            round += 1
            setTimerF(800000)
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
        q1.text = "Новый год"
        q2.text = "Спокойной ночи"
        q3.text = "География"
        q4.text = "Мультфильмы"
        q5.text = "Автомобили"
    }

    private fun twoRound() {
        q1.text = "Зимние виды спорта"
        q2.text = "Фамилии на букву \"М\""
        q3.text = "Россия"
        q4.text = "Фильмы"
        q5.text = "Кухни мира"

    }

    private fun threeRound() {
        q1.text = "Полярники"
        q2.text = "Праздники"
        q3.text = "Животный мир"
        q4.text = "Литература"
        q5.text = "В былые времена"
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
                1 -> Question("Праздничный антипод Нового года", this).load()
                // Хоккей
                2 -> Question("Национальная игра Канады", this).load()
                // Малый ковш
                3 -> Question(
                    "Навигационная Полярная звезда входит в это созвездие",
                    this
                ).load()
            }

            q1v100.visibility = View.INVISIBLE
        }

        q1v200.setOnClickListener {
            when (round) {
                // Германия
                1 -> Question("Страна первой украшенной елочки", this).load()
                // Кёрлинг
                2 -> Question(
                    "Вид спорта, в котором игроки бегают по льду и трут его щетками",
                    this
                ).load()
                // газ
                3 -> Question(
                    "Запасов какого полезного ископаемого больше всего в Арктике?",
                    this
                ).load()
            }

            q1v200.visibility = View.INVISIBLE
        }


        q1v300.setOnClickListener {
            when (round) {
                // дед мороз
                1 -> Question(
                    "Йоулупукки, Пер-Ноэль, Баббо Натале – кто это? ",
                    this
                ).load()
                // Восстанавливает поверхность льда
                2 -> Question(
                    "Что делает машина, название которой Ресурфейсер",
                    this
                ).load()
                // Ленин
                3 -> Question("Как назывался первый атомный ледокол в мире?", this).load()
            }

            q1v300.visibility = View.INVISIBLE
        }

        q1v400.setOnClickListener {
            when (round) {
                // сентябрь
                1 -> Question(
                    "До Петра Великого Новый год начинался в этом месяце",
                    this
                ).load()
                // слалом
                2 -> Question("Вид спуска на лыжах по трассе размеченной воротами", this).load()
                // Баренцево
                3 -> Question(
                    "Именно так называется незамерзающее арктическое море",
                    this
                ).load()
            }

            q1v400.visibility = View.INVISIBLE
        }

        q1v500.setOnClickListener {
            when (round) {
                // поземка
                1 -> Question(
                    "Как называется метель, все время путающаяся под ногами",
                    this
                ).load()
                // брумбол
                2 -> {
                    catQ = "2q1v500"
                    CatScreen.visibility = View.VISIBLE
                    catS!!.start()
                }
                // многолетний лед
                3 -> Question("Арктический пак – это...", this).load()
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
                1 -> Question(
                    "Создатель первой электрической лампочки",
                    this
                ).load()
                // Андрей Миронов
                2 -> Question("Он сыграл Гешу Козодоева", this).load()
                // Зеленый
                3 -> Question(
                    "В день святого Патрика принято одевать такого цвета одежду",
                    this
                ).load()
            }

            q2v100.visibility = View.INVISIBLE
        }

        q2v200.setOnClickListener {
            when (round) {
                // сонник
                1 -> Question("Назовите книгу для занятием ониромантией", this).load()
                // Александр Макдонский
                2 -> Question(
                    "Он покорил Вавилон, Сузу, Персеполь, и стал царем персидской империи",
                    this
                ).load()
                // Туркменистан
                3 -> Question("Назовите страну, в календаре которой есть День дыни", this).load()
            }

            q2v200.visibility = View.INVISIBLE
        }

        q2v300.setOnClickListener {
            when (round) {
                // полярное сияние
                1 -> Question(
                    "Свечение верхних слоев атмосферы при взаимодействии с " +
                            "частицами солнечного ветра, называется именно так",
                    this
                ).load()
                // Карл маркс
                2 -> Question(
                    "Друг и единомышленник Фридриха Энгельса, " +
                            "участник февральской революции 1848г во Франции", this
                ).load()
                // Праздник весны
                3 -> Question("Как еще называют Китайский новый год", this).load()
            }

            q2v300.visibility = View.INVISIBLE
        }

        q2v400.setOnClickListener {
            when (round) {
                // кровать
                1 -> {
                    catQ = "1q2v400"
                    CatScreen.visibility = View.VISIBLE
                    catS!!.start()
                }
                // Вольф Мессинг
                2 -> Question(
                    "Польский гипнотизер и телепат, начинавший как артист цирка",
                    this
                ).load()
                // День святого валентина
                3 -> Question(
                    "В Канаде на этот праздник принято дарить засушенные белые цветы",
                    this
                ).load()
            }

            q2v400.visibility = View.INVISIBLE
        }

        q2v500.setOnClickListener {
            when (round) {
                // Валентина Дворянинова
                1 -> Question("Кто впервые исполнил песню \"Спят усталые игрушки\"", this).load()
                // Мэрилин Монро
                2 -> Question(
                    "Её звани Норма Джин, хотя она была известна под таким имененм",
                    this
                ).load()
                // Каганер
                3 -> Question(
                    "Каталонская фигурка человечков справляющих большую нужду," +
                            " изготавливаемая для рождественских традиций",
                    this
                ).load()
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
                1 -> Question("Столица Бельгии", this).load()
                // Ленинград
                2 -> Question("В каком городе родился президент РФ Путин В.В", this).load()
                // белёк
                3 -> Question("Как называется новорожденный детеныш нерпы?", this).load()
            }

            q3v100.visibility = View.INVISIBLE
        }

        q3v200.setOnClickListener {
            when (round) {
                // Монако
                1 -> Question("Какая из европейских стран-карликов омывается морем?", this).load()
                //Якутия
                2 -> Question(
                    "Где расположено крупнейшее в России месторождение алмазов?",
                    this
                ).load()
                // птица
                3 -> Question("Кто такой коростель?", this).load()
            }

            q3v200.visibility = View.INVISIBLE
        }

        q3v300.setOnClickListener {
            when (round) {
                // Италия
                1 -> Question("Какая страна расположена на Апеннинском полуострове?", this).load()
                // московский Кремль
                2 -> {
                    catQ = "2q3v300"
                    CatScreen.visibility = View.VISIBLE
                    catS!!.start()
                }

                // телескоп
                3 -> Question(
                    "Как называется аквариумная рыбка с глазами, напоминающими линзы?",
                    this
                ).load()
            }

            q3v300.visibility = View.INVISIBLE
        }

        q3v400.setOnClickListener {
            when (round) {
                // Дарваз
                1 -> Question("Рукотворный газовый кратер в Туркменистане", this).load()
                // Челябинск
                2 -> Question("На гербе какого города изображен верблюд", this).load()
                // США
                3 -> Question("В какой стране живет больше всего кошек — 76,5 млн?", this).load()
            }

            q3v400.visibility = View.INVISIBLE
        }

        q3v500.setOnClickListener {
            when (round) {
                //Северный Ледовитый
                1 -> Question("Какой океан Земли занимает наименьшую площадь?", this).load()
                // Певек
                2 -> Question("Самый северный город России", this).load()
                // Новая Гвинея
                3 -> Question("На каком острове обитает медвежий кенгуру?", this).load()
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
                1 -> Question("Какой подарок ко дню рождения Сова подарила ослику Иа?", this).load()
                // Служебный роман
                2 -> Question(
                    "В каком фильме звучит песня «У природы нет плохой погоды»?",
                    this
                ).load()
                // манная
                3 -> Question(
                    "Какую кашу вылил на голову прохожему Дениска, герой Виктора Драгунского из рассказа «Тайное становится явным»?",
                    this
                ).load()
            }

            q4v100.visibility = View.INVISIBLE
        }

        q4v200.setOnClickListener {
            when (round) {
                // свадьба
                1 -> {
                    catQ = "1q4v200"
                    CatScreen.visibility = View.VISIBLE
                    catS!!.start()
                }
                // ку
                2 -> Question(
                    "Какое междометие заменяло большую часть слов жителям планеты Плюк в фильме «Кин-дза-дза!»?",
                    this
                ).load()
                // каштанка
                3 -> Question(
                    "Как звали собачку столяра Луки Александрыча до того, как она попала в цирк и стала Тёткой?",
                    this
                ).load()
            }

            q4v200.visibility = View.INVISIBLE
        }

        q4v300.setOnClickListener {
            when (round) {
                // сурикат
                1 -> Question("К каким животным относится Тимон из «Тимон и Пумба»?", this).load()
                // Горбунков
                2 -> Question(
                    " Какую фамилию носил герой Ю. Никулина в фильме «Бриллиантовая рука»?",
                    this
                ).load()
                // Швейк
                3 -> Question(
                    "Приключения какого бравого солдата описал Ярослав Гашек?",
                    this
                ).load()
            }

            q4v300.visibility = View.INVISIBLE
        }

        q4v400.setOnClickListener {
            when (round) {
                // рюкзак
                1 -> Question(
                    "Что постоянно таскает с собой Лохматый, персонаж «мультов» про Масяню?",
                    this
                ).load()
                // канны
                2 -> Question(
                    "В каком городе Европы проводится самый престижный кинофестиваль?",
                    this
                ).load()
                // Марк Твен
                3 -> Question("Кто автор произведения «Приключения Гекльберри Финна»", this).load()
            }

            q4v400.visibility = View.INVISIBLE
        }

        q4v500.setOnClickListener {
            when (round) {
                // килька
                1 -> Question(
                    "Как назвал золотую рыбку заглавный герой мультфильма «Вовка в Тридевятом царстве»?",
                    this
                ).load()
                // Элефант
                2 -> Question(
                    "Как называлось берлинское кафе где Штирлиц встретился со своей женой?",
                    this
                ).load()
                // Лысые Горы
                3 -> {
                    catQ = "3q4v500"
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
                1 -> Question("Как назывался советский представительский автомобиль", this).load()
                // сало
                2 -> Question("В СССР члены ЦК партии ели ежедневно 50 грамм этого", this).load()
                // Екатеринбург
                3 -> Question(
                    "Какой город в советское время назывался Свердловском?",
                    this
                ).load()
            }

            q5v100.visibility = View.INVISIBLE
        }

        q5v200.setOnClickListener {
            when (round) {
                // полный
                1 -> Question(
                    "Какой привод является отличительной особенностью автомобиля-внедорожника?",
                    this
                ).load()
                // Австрийской
                2 -> Question("Блюдом какой национальной кухни является штрудель?", this).load()
                // М. С. Горбачев
                3 -> Question(
                    "Кто из генеральных секретарей ЦК КПСС получил в народе прозвище минеральный секретарь?",
                    this
                ).load()
            }

            q5v200.visibility = View.INVISIBLE
        }

        q5v300.setOnClickListener {
            when (round) {
                // Венгрии
                1 -> Question(
                    "В какой стране бывшего социалистического лагеря производились автобусы Икарус?",
                    this
                ).load()
                // Украина
                2 -> Question(
                    "Банош - ароматная кукурузная каша со сметаной и сливками, это национальное блюдо какой страны?",
                    this
                ).load()
                3 -> Question("", this).load()
            }

            q5v300.visibility = View.INVISIBLE
        }

        q5v400.setOnClickListener {
            when (round) {
                // Фритта
                1 -> Question(
                    "Как называются чёрные точки, расположенные по периметру автомобильных стекол?",
                    this
                ).load()
                // Каравай
                2 -> Question("Какое из этих слов означает то же, что и «коврига»?", this).load()
                // 100гр для храбрости
                3 -> Question(
                    "Как называется советский сатирический киноальманах из трёх новелл, снятый в 1976 году?",
                    this
                ).load()
            }

            q5v400.visibility = View.INVISIBLE
        }

        q5v500.setOnClickListener {
            when (round) {
                // Миасс
                1 -> Question("В каком городе выпускают грузовые автомобили «Урал»?", this).load()
                // телятина
                2 -> Question("Из какого мяса делается настоящий шницель по-венски?", this).load()
                // Пищепром
                3 -> {
                    catQ = "3q5v500"
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
}