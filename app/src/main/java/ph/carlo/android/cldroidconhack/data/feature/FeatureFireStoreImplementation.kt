package ph.carlo.android.cldroidconhack.data.feature

import android.util.Log
import com.google.firebase.database.collection.ArraySortedMap
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class FeatureFireStoreImplementation: FeatureFeedAPIInterface {
    override fun getFeatureFeed(): Observable<FeatureFeed> {
        return Observable.create {
            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("dcfeatures").document("features")
            docRef.get()
                .addOnSuccessListener { document ->
                    val player = document.data?.get("player") as HashMap<String, Any>

                    val map = HashMap<String, Any>()

                    val playerMap = HashMap<String, Any>()

                    playerMap["exoplayer"] = player["exoplayer"] as Boolean
                    playerMap["mediaplayer"] = player["mediaplayer"] as Boolean

                    map["player"] = playerMap
                    it.onNext(FeatureFeed(map))
                    it.onComplete()

                }
                .addOnFailureListener { exception ->
                    it.onError(exception)
                }
        }
    }

}