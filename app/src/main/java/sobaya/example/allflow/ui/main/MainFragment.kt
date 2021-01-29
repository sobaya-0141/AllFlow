package sobaya.example.allflow.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.wada811.databinding.dataBinding
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ldralighieri.corbind.widget.textChanges
import sobaya.example.allflow.R
import sobaya.example.allflow.databinding.MainFragmentBinding
import sobaya.example.allflow.util.Dispatcher

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModel()
    private val binding: MainFragmentBinding by dataBinding()
    private val dispatcher: Dispatcher by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.userName.observe(viewLifecycleOwner, Observer {
            it.toString()
        })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            dispatcher.testSend("何かのイベント1")
            viewModel.recieve()
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            dispatcher.testSend("何かのイベント2")
        }
    }
}
