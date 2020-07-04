package tk.zwander.autofillchanger

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(Settings.ACTION_REQUEST_SET_AUTOFILL_SERVICE)

        //Need to specify a package Uri even if it's just a random
        //package name.
        intent.data = Uri.parse("package:$packageName")

        try {
            startActivity(intent)
            finish()
        } catch (e: Exception) {
            AlertDialog.Builder(this)
                    .setTitle(R.string.failed_to_launch)
                    .setMessage(resources.getString(R.string.failed_to_launch_desc, e.message))
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        finish()
                    }
                    .show()
        }
    }
}