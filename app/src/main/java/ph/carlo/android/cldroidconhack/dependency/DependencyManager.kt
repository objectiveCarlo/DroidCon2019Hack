package ph.carlo.android.cldroidconhack.dependency

import ph.carlo.android.cldroidconhack.BuildConfig
import ph.carlo.android.cldroidconhack.data.feature.FeatureFeedAPIInterface
import ph.carlo.android.cldroidconhack.data.feature.FeatureFeedMyJsonAPI
import ph.carlo.android.cldroidconhack.data.feature.FeatureFeedMock
import ph.carlo.android.cldroidconhack.data.feature.FeatureFireStoreImplementation

object DependencyManager {
    fun getFeatureFeedAPI(): FeatureFeedAPIInterface {
        return when (BuildConfig.FEED_SOURCE) {
            3 -> FeatureFeedMock()
            2 -> FeatureFireStoreImplementation()
            else -> FeatureFeedMyJsonAPI()
        }
    }
}