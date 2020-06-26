package sobaya.example.allflow

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import sobaya.example.allflow.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            test()
        } else {
            println("もう権限もってる")
        }
    }

    fun test() {
        val rp = ActivityResultContracts.RequestPermission()
        val getContent = registerForActivityResult(rp) {
            if (it) {
                println("権限取得")
            } else {
                println("だめだった")
            }
        }
        getContent.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}