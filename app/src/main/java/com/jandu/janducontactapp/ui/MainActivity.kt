package com.jandu.janducontactapp.ui


import android.Manifest.permission.READ_CONTACTS
import android.Manifest.permission.READ_SMS
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jandu.janducontactapp.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    /*@Inject
    lateinit var viewModeFactory: ViewModelFactory*/

    //lateinit var myViewModel: MainActivityViewModel


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //myViewModel = ViewModelProviders.of(this, this.viewModeFactory).get(MainActivityViewModel::class.java)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainActivityViewModel::class.java)

        checkPermission()
    }

    private fun checkPermission() {

        if ((checkSelfPermission(READ_CONTACTS) == PERMISSION_GRANTED) &&
            (checkSelfPermission(READ_SMS) == PERMISSION_GRANTED)) {

            //startActivity(Intent(this, StartActivity::class.java))

            contact_permission_button.isEnabled = false
            sms_permission_button.isEnabled = false

            contact_permission_button.background = getDrawable(R.color.green)
            sms_permission_button.background = getDrawable(R.color.green)

            contact_permission_button.text = getText(R.string.granted)
            sms_permission_button.text = getText(R.string.granted)

        } else if ((checkSelfPermission(READ_CONTACTS) == PERMISSION_GRANTED) &&
            (checkSelfPermission(READ_SMS) != PERMISSION_GRANTED)){

            contact_permission_button.isEnabled = false
            sms_permission_button.isEnabled = true

            contact_permission_button.background = getDrawable(R.color.green)
            sms_permission_button.background = getDrawable(R.color.red)


        } else if ((checkSelfPermission(READ_CONTACTS) != PERMISSION_GRANTED) &&
            (checkSelfPermission(READ_SMS) == PERMISSION_GRANTED)){

            contact_permission_button.isEnabled = true
            sms_permission_button.isEnabled = false

            contact_permission_button.background = getDrawable(R.color.red)
            sms_permission_button.background = getDrawable(R.color.green)

        }else{

            contact_permission_button.isEnabled = true
            sms_permission_button.isEnabled = true

            contact_permission_button.background = getDrawable(R.color.red)
            sms_permission_button.background = getDrawable(R.color.red)

        }

        contact_permission_button.setOnClickListener {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(READ_CONTACTS),
                46
            )
        }

        sms_permission_button.setOnClickListener {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(READ_SMS),
                47
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray) {

        if (requestCode == 46){

            if (grantResults[0] == 0) {

                contact_permission_button.isEnabled = false
                contact_permission_button.background = getDrawable(R.color.green)
            }
            else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_CONTACTS)) {
                    Toast.makeText(this, "This Permission is important",
                        Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", packageName, null)
                    intent.setData(uri)
                    startActivity(intent)
                }
            }
        }else if(requestCode == 47){

            if (grantResults[0] == 0) {

                sms_permission_button.isEnabled = false
                sms_permission_button.background = getDrawable(R.color.green)

            }
            else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, READ_SMS)) {
                    Toast.makeText(this, "This Permission is important",
                        Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts("package", packageName, null)
                    intent.setData(uri)
                    startActivity(intent)
                }
            }
        }

        if ((checkSelfPermission(READ_CONTACTS) == PERMISSION_GRANTED) &&
            (checkSelfPermission(READ_SMS) == PERMISSION_GRANTED)) {

           /* myViewModel.loadData().observe(this, Observer {

                Log.i("APPDATA", it.get(0).mobileNumber.toString())

            })*/

            viewModel.getData().observe(this, Observer {

                Log.i("APPDATA", it)
            })

        }
    }
}
