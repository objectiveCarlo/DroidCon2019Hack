package ph.carlo.android.cldroidconhack.dependency

import ph.carlo.android.cldroidconhack.BuildConfig
import ph.carlo.android.cldroidconhack.data.feature.FeatureFeedAPIInterface
import ph.carlo.android.cldroidconhack.data.feature.FeatureFeedImplementation
import ph.carlo.android.cldroidconhack.data.feature.FeatureFeedMock

object DependencyManager {
    fun getFeatureFeedAPI(): FeatureFeedAPIInterface {
        return when (BuildConfig.FEED_SOURCE) {
            3 -> FeatureFeedMock()
            else -> FeatureFeedImplementation()
        }
    }
}