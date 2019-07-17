package ph.carlo.android.cldroidconhack.data.feature

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET


interface FeatureFeedAPIService {
    @GET("hmmh5")
    fun getAllFeatures(): Call<FeatureFeedAPI>
}
class FeatureFeedAPI {
    companion object{
        const val BASE_URL = "https://api.myjson.com/bins/"
    }

    @SerializedName("features")
    var features: HashMap<String, String>? = null
}