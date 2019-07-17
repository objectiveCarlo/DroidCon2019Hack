package ph.carlo.android.cldroidconhack.data.feature

import io.reactivex.Observable

interface FeatureFeedAPIInterface {
    fun getFeatureFeed(): Observable<FeatureFeed>
}