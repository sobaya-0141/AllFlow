package sobaya.example.allflow.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.review.testing.FakeReviewManager
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.ldralighieri.corbind.view.clicks
import ru.ldralighieri.corbind.widget.textChanges
import sobaya.example.allflow.R

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
        view.findViewById<EditText>(R.id.message)
            .textChanges()
            .debounce(1000)
            .onEach {
                if (it.isEmpty()) return@onEach
                viewModel.getRepo(it.toString())
            }.launchIn(viewLifecycleOwner.lifecycleScope)

        view.findViewById<Button>(R.id.btnReview)
            .clicks()
            .onEach {
//                val manager = ReviewManagerFactory.create(requireContext())
                val manager = FakeReviewManager(requireContext())
                val request = manager.requestReviewFlow()
                request.addOnCompleteListener { request ->
                    if (request.isSuccessful) {
                        val reviewInfo = request.result
                        val flow = manager.launchReviewFlow(requireActivity(), reviewInfo)
                        flow.addOnCompleteListener { _ ->
                            Toast.makeText(requireContext(), "終了", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // 問題が発生したけど気にしたらいけない
                    }
                }

            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}