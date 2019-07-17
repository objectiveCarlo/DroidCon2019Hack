package ph.carlo.android.cldroidconhack.data.feature

import io.reactivex.Observable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class FeatureFeedMock: FeatureFeedAPIInterface {
    override fun getFeatureFeed(): Observable<FeatureFeed> {
      return Observable.create {
          val mockedString = "{\"player\":{\"exoplayer\":true,\"mediaplayer\":true}}"
          val gson = Gson()
          val empMapType = object : TypeToken<HashMap<String, HashMap<String, Any>>>() {
          }.type
          val map = gson.fromJson<HashMap<String, Any>>(mockedString, empMapType)
          it.onNext(FeatureFeed(map))
      }
    }

}