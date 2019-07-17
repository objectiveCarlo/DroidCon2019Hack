package ph.carlo.android.cldroidconhack.domain

import io.reactivex.Observable
import ph.carlo.android.cldroidconhack.data.feature.FeatureFeed
import ph.carlo.android.cldroidconhack.data.feature.FeatureFeedRepository

class GetFeatureFeed {
    fun getFeatureFeed(): Observable<FeatureFeed> {
        return FeatureFeedRepository.getFeatureFeed()
    }
}