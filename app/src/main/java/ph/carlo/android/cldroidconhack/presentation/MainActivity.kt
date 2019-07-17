package ph.carlo.android.cldroidconhack.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import kotlinx.android.synthetic.main.activity_main.*
import ph.carlo.android.cldroidconhack.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)
        launch_player.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            this.startActivity(intent)
        }
    }
}
