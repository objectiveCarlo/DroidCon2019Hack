package ph.carlo.android.cldroidconhack.data.feature

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface FeatureFeedAPIService {
    @GET("hmmh5")
    fun getAllFeatures(): Call<FeatureFeedImplementation>
}
class FeatureFeedImplementation: FeatureFeedAPIInterface {
    override fun getFeatureFeed(): Observable<FeatureFeed> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(FeatureFeedAPIService::class.java)
        val call = service.getAllFeatures()

        return Observable.create {
            call.enqueue(object : Callback<FeatureFeedImplementation>{
                override fun onFailure(call: Call<FeatureFeedImplementation>, t: Throwable) {
                    it.onError(t)
                }

                override fun onResponse(
                    call: Call<FeatureFeedImplementation>,
                    response: Response<FeatureFeedImplementation>
                ) {
                    if (response.code() == 200) {
                        val featureFeedImplementation = response.body()!!
                        it.onNext(FeatureFeed(featureFeedImplementation.features!!))
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

    @SerializedName("features")
    var features: HashMap<String, String>? = null
}