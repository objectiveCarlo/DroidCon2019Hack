package ph.carlo.android.cldroidconhack.presentation

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ph.carlo.android.cldroidconhack.R

class PlayerActivity : AppCompatActivity(), FeatureMVPView{
    override fun whichPlayerFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.player_content, fragment)
        fragmentTransaction.commit()
    }

    override fun getContext(): Context {
       return this
    }

    private val featurePresenter: FeaturePresenter = FeaturePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        setContentView(R.layout.activity_player)
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
