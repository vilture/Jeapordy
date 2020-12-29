package ru.vilture.jeapordy

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.CountDownTimer
import java.util.*
import java.util.concurrent.TimeUnit

class Question(question: String, ctx: Context) {
    var ad = AlertDialog.Builder(ctx).setMessage(question)
    var context = ctx

    fun load() {


        ad.setNegativeButton(
            "Ожидание ответа"
        ) { dialog, which ->
            dialog.dismiss()
        }
        ad.setCancelable(false)

        val listener = ad.create()
        listener.setOnShowListener(DialogTimeoutListener(context))
        listener.show()
    }

}

private class DialogTimeoutListener(context: Context) : DialogInterface.OnShowListener,
    DialogInterface.OnDismissListener {
    private lateinit var mCountDownTimer: CountDownTimer
    private var ctx = context

    override fun onShow(dialog: DialogInterface) {
        val defaultButton = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_NEGATIVE)
        val positiveButtonText = defaultButton.text
        mCountDownTimer = object : CountDownTimer(AUTO_DISMISS_MILLIS.toLong(), 100) {
            override fun onTick(millisUntilFinished: Long) {
                defaultButton.text = String.format(
                    Locale.getDefault(), "%s (%d)",
                    positiveButtonText,
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) + 1
                )

            }

            override fun onFinish() {
                if (dialog.isShowing) {
                    val sound = MediaPlayer.create(ctx, R.raw.si_timeout)
                    sound.start()
                    defaultButton.text = "Время вышло"
                }
            }
        }
        mCountDownTimer.start()
    }

    override fun onDismiss(dialog: DialogInterface) {
        mCountDownTimer.cancel()
    }

    companion object {
        private const val AUTO_DISMISS_MILLIS = 1 * 60 * 1000
    }
}




