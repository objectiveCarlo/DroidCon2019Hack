package ph.carlo.android.cldroidconhack.data.feature

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import com.google.gson.GsonBuilder
import com.google.gson.Gson




interface FeatureFeedAPIService {
    @GET("hmmh5")
    fun getAllFeatures(): Call<FeatureFeedResponse>
}

class PlayerFeatureResponse {
    @SerializedName("exoplayer")
    var exoPlayer: Boolean? = null

    @SerializedName("mediaplayer")
    var mediaPlayer: Boolean? = null
}

class FeaturesFeedResponse {
    @SerializedName("player")
    var player: PlayerFeatureResponse? = null
}
class FeatureFeedResponse {
    @SerializedName("features")
    var features: FeaturesFeedResponse? = null
}

class FeatureFeedMyJsonAPI: FeatureFeedAPIInterface {
    override fun getFeatureFeed(): Observable<FeatureFeed> {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val service = retrofit.create(FeatureFeedAPIService::class.java)
        val call = service.getAllFeatures()

        return Observable.create {
            call.enqueue(object : Callback<FeatureFeedResponse>{
                override fun onFailure(call: Call<FeatureFeedResponse>, t: Throwable) {
                    it.onError(t)
                }

                override fun onResponse(
                    call: Call<FeatureFeedResponse>,
                    response: Response<FeatureFeedResponse>
                ) {
                    if (response.code() == 200) {
                        val featureFeedResponse = response.body()!!
                        val playerFeatures = featureFeedResponse.features?.player
                        val features = HashMap<String, Any>()
                        val player = HashMap<String, Any>()
                        player["exoplayer"] = playerFeatures!!.exoPlayer!!
                        player["mediaplayer"] = playerFeatures!!.mediaPlayer!!
                        features["player"] = player
                        it.onNext(FeatureFeed(features))
                        it.onComplete()
                    }  else {
                        it.onError(Error())
                    }
                }

            })
        }
    }

    companion object{
        const val BASE_URL = "https://api.myjson.com/bins/"
    }

}