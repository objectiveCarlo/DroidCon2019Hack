package ph.carlo.android.cldroidconhack.presentation

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.internal.LinkedHashTreeMap
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ph.carlo.android.cldroidconhack.data.feature.FeatureFeed
import ph.carlo.android.cldroidconhack.domain.GetFeatureFeed
import ph.carlo.android.cldroidconhack.presentation.player.ExoPlayerFragment
import ph.carlo.android.cldroidconhack.presentation.player.MediaPlayerFragment

interface FeatureMVPView {
    fun whichPlayerFragment(fragment: Fragment)
    fun getContext(): Context
}

class FeaturePresenter(val mvpView: FeatureMVPView) {
    val getFeatureFeed = GetFeatureFeed()
    var disposable: Disposable? = null

    fun refresh() {
            getFeatureFeed.getFeatureFeed()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(observer())
    }
    private fun observer(): Observer<FeatureFeed>{
        return object: Observer<FeatureFeed>{
            override fun onComplete() {
                disposable = null
            }

            override fun onSubscribe(d: Disposable) {
                disposable = disposable
            }

            override fun onNext(t: FeatureFeed) {
                interpret(t)
            }

            override fun onError(e: Throwable) {

            }

        }
    }

    private fun interpret(featureFeed: FeatureFeed) {
       val features = featureFeed.features

       val playerFeatures = features["player"]!! as HashMap<String, Any>

       val exoIsOn = playerFeatures["exoplayer"]!! as Boolean
       val mediaIsOn = playerFeatures["mediaplayer"]!! as Boolean
       var toast = "Media player for landscape"
       if (exoIsOn) {
           toast = "Exo player for landscape"
           initializeExo()
       } else {
           if (mediaIsOn) {
               initializeMedia()
           }
       }

        Toast.makeText(mvpView.getContext(), toast, Toast.LENGTH_LONG).show()
    }

    private fun initializeExo() {
        mvpView.whichPlayerFragment(ExoPlayerFragment())
    }

    private fun initializeMedia() {
        mvpView.whichPlayerFragment(MediaPlayerFragment())
    }

    fun turnOff() {
        disposable?.dispose()
    }
}