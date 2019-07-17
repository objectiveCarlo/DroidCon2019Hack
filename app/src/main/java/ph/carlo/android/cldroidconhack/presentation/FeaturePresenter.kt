package ph.carlo.android.cldroidconhack.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.gson.internal.LinkedHashTreeMap
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ph.carlo.android.cldroidconhack.data.feature.FeatureFeed
import ph.carlo.android.cldroidconhack.domain.GetFeatureFeed

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

       if (exoIsOn) {
           initializeExo()
       } else {
           if (mediaIsOn) {
               initializeMedia()
           }
       }
    }

    private fun initializeExo() {

    }

    private fun initializeMedia() {

    }

    fun turnOff() {
        disposable?.dispose()
    }
}