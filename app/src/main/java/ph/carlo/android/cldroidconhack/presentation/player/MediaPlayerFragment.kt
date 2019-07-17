package ph.carlo.android.cldroidconhack.presentation.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_media_player, container, false)
    }

}
