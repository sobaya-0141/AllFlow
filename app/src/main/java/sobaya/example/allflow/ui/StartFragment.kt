package sobaya.example.allflow.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import sobaya.example.allflow.MainActivity
import sobaya.example.allflow.R

class StartFragment: Fragment(R.layout.start_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).resetIsLogin()
        view.findViewById<Button>(R.id.button2).setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }
    }
}