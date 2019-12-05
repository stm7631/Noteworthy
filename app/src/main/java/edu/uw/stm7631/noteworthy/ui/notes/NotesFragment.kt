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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notes, container, false)
        val textView: TextView = root.findViewById(R.id.text_view_date)
        textView.text = current_date()
        return root
    }


    fun current_date(): String {
        return Calendar.getInstance().time.toString()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        save.setOnClickListener {
            Toast.makeText(
                this.context, "Note is saved.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}







