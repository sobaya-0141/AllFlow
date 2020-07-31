package sobaya.example.allflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sobaya.example.allflow.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private var isLogin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    fun resetIsLogin() {
        isLogin = false
    }

    fun getIsLogin(): Boolean {
        val ret = isLogin
        isLogin = true
        return ret
    }
}