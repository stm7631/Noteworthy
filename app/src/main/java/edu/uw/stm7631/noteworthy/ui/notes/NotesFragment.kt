package edu.uw.stm7631.noteworthy.ui.notes
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.*
import android.widget.TextView
import java.util.Calendar
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import edu.uw.stm7631.noteworthy.R
import kotlinx.android.synthetic.main.fragment_courses.*
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment : Fragment() {

    private var paramData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramData = it.getString("note")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_notes, container, false)
        val textView: TextView = root.findViewById(R.id.text_view_date)
        textView.text = Calendar.getInstance().time.toString()
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        save.setOnClickListener {
            Toast.makeText(
                this.context, "Note saved!",
                Toast.LENGTH_LONG
            ).show()
        }

        text_content.apply {
            setText(paramData)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(paramData: String) =
            NotesFragment().apply {
                arguments = Bundle().apply {
                    putString("note", paramData)
                }
            }
    }
}







