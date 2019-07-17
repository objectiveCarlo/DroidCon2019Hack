package ph.carlo.android.cldroidconhack.data.feature

import io.reactivex.Observable

object FeatureFeedRepository {

    private fun getDependency(): FeatureFeedAPIInterface {
        return FeatureFeedMock()
    }

    fun getFeatureFeed(): Observable<FeatureFeed> {
        return getDependency().getFeatureFeed()
    }
}