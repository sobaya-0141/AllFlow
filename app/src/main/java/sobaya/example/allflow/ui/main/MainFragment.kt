package sobaya.example.allflow.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ldralighieri.corbind.widget.textChanges
import sobaya.example.allflow.MainActivity
import sobaya.example.allflow.R
import sobaya.example.allflow.ui.login.LoginFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val stateHandle = navController.currentBackStackEntry!!.savedStateHandle
        stateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
            .observe(viewLifecycleOwner, Observer { success ->
                if (success) {
                    loadData()
                } else {
                    val startDistination = navController.graph.startDestination
                    navController.navigate(startDistination, null, navOptions {
                        popUpTo(startDistination) {
                            inclusive = true
                        }
                    })
                }
        })

        if (!(activity as MainActivity).getIsLogin()) {
            navController.navigate(R.id.action_mainFragment_to_loginFragment)
        }
    }

    private fun loadData() {
        Snackbar.make(requireView(), "ログインしたよ", Snackbar.LENGTH_SHORT)
        requireView().findViewById<EditText>(R.id.message)
            .textChanges()
            .debounce(1000)
            .onEach {
                if (it.isEmpty()) return@onEach
                viewModel.getRepo(it.toString())
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}