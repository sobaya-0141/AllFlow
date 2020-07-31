package sobaya.example.allflow.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import sobaya.example.allflow.R

class LoginFragment: Fragment(R.layout.login_fragment) {

    companion object {
        const val LOGIN_SUCCESSFUL = "login_successful"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val savedStateHandle = navController.previousBackStackEntry!!.savedStateHandle

        view.findViewById<Button>(R.id.button).setOnClickListener {
            savedStateHandle.set(LOGIN_SUCCESSFUL, true)
            navController.popBackStack()
        }
        view.findViewById<Button>(R.id.button3).setOnClickListener {
            savedStateHandle.set(LOGIN_SUCCESSFUL, false)
            navController.popBackStack()
        }
    }
}