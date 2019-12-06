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
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import edu.uw.stm7631.noteworthy.CourseContent.USER
import edu.uw.stm7631.noteworthy.R
import kotlinx.android.synthetic.main.fragment_courses.*
import kotlinx.android.synthetic.main.fragment_notes.*
import java.util.ArrayList


class NotesFragment : Fragment() {      //creates a Notes fragment

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
        textView.text = Calendar.getInstance().time.toString() //displays the date and time on top of the notes screen.
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        save.setOnClickListener {  //performs action when user clicks on floating action button.
            Toast.makeText(
                this.context, R.string.saved, //Shows toast message 'Note saved' when user taps on floating action button with checkmark.
                Toast.LENGTH_LONG
            ).show()
            var db = FirebaseFirestore.getInstance()
            // Send the new note to Firebase
            db.collection("notes").add(hashMapOf(
                "class" to db.document("/classes/" + note_course_name.selectedItem.toString().replace("\\s".toRegex(), "")),
                "text" to text_content.text.toString(),
                "title" to text_heading.text.toString(),
                "author" to USER
            ))
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







