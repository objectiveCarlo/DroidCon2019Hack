package ph.carlo.android.cldroidconhack.data.feature

import io.reactivex.Observable
import ph.carlo.android.cldroidconhack.dependency.DependencyManager

object FeatureFeedRepository {

    private fun getDependency(): FeatureFeedAPIInterface {
        return DependencyManager.getFeatureFeedAPI()
    }

    fun getFeatureFeed(): Observable<FeatureFeed> {
        return getDependency().getFeatureFeed()
    }
}