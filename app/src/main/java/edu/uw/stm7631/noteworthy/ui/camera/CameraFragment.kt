package edu.uw.stm7631.noteworthy.ui.camera

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import edu.uw.stm7631.noteworthy.CameraActivity

class CameraFragment : Fragment() {

    //launch camera vision activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(getContext(), CameraActivity::class.java)
        startActivity(intent)
    }



}