package sobaya.example.allflow.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Recomposer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import sobaya.example.allflow.R

class ComposeSample @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.compose, this, true)
        (view as ViewGroup).setContent(Recomposer.current(), null) {
            MaterialTheme {
                customView()
            }
        }
    }

    @Composable
    fun customView() {
        Text(text = "TEST", color = Color.Black)
    }
}
