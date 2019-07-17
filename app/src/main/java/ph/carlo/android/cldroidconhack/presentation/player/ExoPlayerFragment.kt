package ph.carlo.android.cldroidconhack.presentation.player

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.ogg.OggExtractor
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.LoopingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import kotlinx.android.synthetic.main.fragment_exo_player.*
import ph.carlo.android.cldroidconhack.R


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ExoPlayerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ExoPlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ExoPlayerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exo_player, container, false)
    }

    override fun onResume() {
        super.onResume()
        initExoPlayer()
    }

    private fun initExoPlayer() {
        val player = ExoPlayerFactory.newSimpleInstance(context)

        val dataSpec = DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.droidcon))
        val rawResourceDataSource = RawResourceDataSource(context)
        rawResourceDataSource.open(dataSpec)

        val factory = DataSource.Factory { rawResourceDataSource }
        val mainMediaSource = ProgressiveMediaSource.Factory(factory).createMediaSource(rawResourceDataSource.uri)

        val loopingMediaSource = LoopingMediaSource(mainMediaSource)
        player.prepare(loopingMediaSource)
        player.playWhenReady = true

        playerView.player = player
    }

}
