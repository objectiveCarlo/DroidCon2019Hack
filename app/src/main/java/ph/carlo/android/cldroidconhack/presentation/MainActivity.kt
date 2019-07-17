package ph.carlo.android.cldroidconhack.presentation

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*
import ph.carlo.android.cldroidconhack.R


class MainActivity : AppCompatActivity() {

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        launch_player.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            this.startActivity(intent)
        }

        surfaceView.holder.addCallback(object: SurfaceHolder.Callback{
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {

            }

            override fun surfaceCreated(p0: SurfaceHolder?) {
                startMediaPlayer()
            }

        })
    }


    override fun onPause() {
        super.onPause()
        if (mediaPlayer != null) {
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    private fun startMediaPlayer() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.droidcon)
            val surfaceHolder = surfaceView.holder
            mediaPlayer?.setDisplay(surfaceHolder)
            mediaPlayer?.isLooping = true
            runOnUiThread {
                mediaPlayer?.setOnPreparedListener {
                    it.start()
                }
                mediaPlayer?.setOnVideoSizeChangedListener { mp, width, height -> setFitToFillAspectRatio(mp, width, height) }
                mediaPlayer?.prepareAsync()

            }
        } catch (e: Exception) {
//            Toast.makeText(applicationContext, e.message, Toast.LENGTH_LONG).show()
        }

    }


    private fun setFitToFillAspectRatio(mp: MediaPlayer?, videoWidth: Int, videoHeight: Int) {
        if (mp != null) {
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val screenHeight = displayMetrics.heightPixels
            val screenWidth = displayMetrics.widthPixels
            val videoParams = surfaceView.layoutParams


//            if (videoWidth > videoHeight) {
//                videoParams.width = screenWidth
//                videoParams.height = screenWidth * videoHeight / videoWidth
//            } else {
                videoParams.width = screenHeight * videoWidth / videoHeight
                videoParams.height = screenHeight
//            }


            surfaceView.layoutParams = videoParams
        }
    }

}
