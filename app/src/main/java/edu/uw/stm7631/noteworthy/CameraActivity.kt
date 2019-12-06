package edu.uw.stm7631.noteworthy

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
//import android.support.v4.app.ActivityCompat
//import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.text.TextBlock
import com.google.android.gms.vision.text.TextRecognizer
import java.io.IOException

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.camera_main.*
import kotlinx.android.synthetic.main.fragment_notes.*

class CameraActivity : AppCompatActivity() {
    var mCameraView: SurfaceView? = null
    var mTextView: TextView? = null
    var mCameraSource: CameraSource? = null
    var savedText: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_main)
        mCameraView = findViewById(R.id.surfaceView)
        mTextView = findViewById(R.id.text_view)
        startCameraSource()

        save_text.setOnClickListener {
            if (savedText != null) {
                val intent = Intent(this, SwitchableActivity::class.java)
                intent.putExtra("Photo note", savedText)
                startActivity(intent)
            }
        }
    }

    //based off of open-source code & google vision
    //original author: Prakash Pun
    //https://medium.com/@prakash_pun/text-recognition-for-android-using-google-mobile-vision-a8ffabe3f5d6

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != requestPermissionID) {
            Log.d(TAG, "Got unexpected permission result: $requestCode")
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            try {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return
                }
                mCameraSource!!.start(mCameraView!!.holder)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun startCameraSource() { //Create the TextRecognizer
        val textRecognizer = TextRecognizer.Builder(applicationContext).build()
        if (!textRecognizer.isOperational) {
            Log.w(TAG, "Detector dependencies not loaded yet")
        } else { //Initialize camerasource to use high resolution and set Autofocus on.
            mCameraSource = CameraSource.Builder(applicationContext, textRecognizer)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1280, 1024)
                .setAutoFocusEnabled(true)
                .setRequestedFps(2.0f)
                .build()
            /**
             * Add call back to SurfaceView and check if camera permission is granted.
             * If permission is granted we can start our cameraSource and pass it to surfaceView
             */
            mCameraView!!.holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceCreated(holder: SurfaceHolder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(applicationContext,
                                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(this@CameraActivity, arrayOf(Manifest.permission.CAMERA),
                                requestPermissionID)
                            return
                        }
                        mCameraSource?.start(mCameraView!!.holder)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
                override fun surfaceDestroyed(holder: SurfaceHolder) {
                    mCameraSource?.stop()
                }
            })
            //Set the TextRecognizer's Processor.
            textRecognizer.setProcessor(object : Detector.Processor<TextBlock> {
                override fun release() {}
                /**
                 * Detect all the text from camera using TextBlock and the values into a stringBuilder
                 * which will then be set to the textView.
                 */
                override fun receiveDetections(detections: Detections<TextBlock>) {
                    val items = detections.detectedItems
                    Thread.sleep(900)
                    if (items.size() != 0) {
                        mTextView!!.post {
                            val stringBuilder = StringBuilder()
                            for (i in 0 until items.size()) {
                                val item = items.valueAt(i)
                                stringBuilder.append(item.value)
                                stringBuilder.append("\n")
                            }
                            savedText = stringBuilder.toString()
                            mTextView!!.text = savedText
                        }
                    }
                }
            })
        }
    }

    companion object {
        private const val TAG = "CameraActivity"
        private const val requestPermissionID = 101
    }
}