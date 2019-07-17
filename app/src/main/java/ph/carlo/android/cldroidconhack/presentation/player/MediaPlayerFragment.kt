package ph.carlo.android.cldroidconhack.presentation.player

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_media_player.view.*
import ph.carlo.android.cldroidconhack.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MediaPlayerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MediaPlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MediaPlayerFragment : Fragment() {

    var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_media_player, container, false)
        view.surfaceView.holder.addCallback(object: SurfaceHolder.Callback{
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {

            }

            override fun surfaceCreated(p0: SurfaceHolder?) {
                startMediaPlayer()
            }

        })
        return view
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
            mediaPlayer = MediaPlayer.create(activity, R.raw.droidcon)
            val surfaceHolder = surfaceView.holder
            mediaPlayer?.setDisplay(surfaceHolder)
            mediaPlayer?.isLooping = true
            activity?.runOnUiThread {
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
            activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            val screenHeight = displayMetrics.heightPixels
            val screenWidth = displayMetrics.widthPixels
            val videoParams = surfaceView.layoutParams


            if (videoWidth > videoHeight) {
                videoParams.width = screenWidth
                videoParams.height = screenWidth * videoHeight / videoWidth
            } else {
                videoParams.width = screenHeight * videoWidth / videoHeight
                videoParams.height = screenHeight
            }


            surfaceView.layoutParams = videoParams
        }
    }

}
