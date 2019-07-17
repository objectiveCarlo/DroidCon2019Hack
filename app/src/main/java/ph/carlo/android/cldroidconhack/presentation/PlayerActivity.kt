package ph.carlo.android.cldroidconhack.presentation

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import ph.carlo.android.cldroidconhack.R

import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity(), FeatureMVPView{
    override fun whichPlayerFragment(fragment: Fragment) {

    }

    override fun getContext(): Context {
       return this
    }

    private val featurePresenter: FeaturePresenter = FeaturePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        setSupportActionBar(toolbar)
    }

    override fun onResume() {
        super.onResume()
        featurePresenter.refresh()
    }

    override fun onPause() {
        super.onPause()
        featurePresenter.turnOff()
    }

}
