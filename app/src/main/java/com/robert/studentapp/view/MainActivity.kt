package com.robert.studentapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.robert.studentapp.R
import com.robert.studentapp.databinding.ActivityMainBinding
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.robert.studentapp.util.createNotificationChannel
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //init dipanggil sebelum onCreate ketika main activity dijalankan
    //merupakan salah satu cara untuk memanggil notifikasi: bergantung ke main activity
    //main activity destroy, app crash
    init {
        instance = this

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")
    }

    companion object {
        //instance berjenis diri sendiri, bertipe static
        private var instance: MainActivity? = null

        fun showNotification(title: String, content: String, icon: Int) {
            //membuat channel id, bebas isi channel idnya
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val notificationBuilder =
                NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)

                }
            val manager = NotificationManagerCompat.from(instance!!.applicationContext)
            if (ActivityCompat.checkSelfPermission(instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS ) !=
                PackageManager.PERMISSION_GRANTED){
                //maksa user untuk izinin lagi buat kirim notifikasi (runtime permission)
                //request code bebas
                ActivityCompat.requestPermissions(instance!!,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
                return
            }

            manager.notify(1001, notificationBuilder.build())
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //function dipanggil ketika user allow/deny permission
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 1){
            if(grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                //kasus ketika user memilih cancel, buka allow/deny

                Log.d("permission_check", "allow")

                //create notification channel
                createNotificationChannel(this,
                    NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                    getString(R.string.app_name), "App notification channel.")


            }

            else{
                Log.d("permission_check", "deny")
            }
        }

    }
}