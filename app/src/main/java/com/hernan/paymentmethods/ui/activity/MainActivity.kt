package com.hernan.paymentmethods.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.hernan.paymentmethods.R
import com.hernan.paymentmethods.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.FileInputStream
import java.io.IOException


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = resources.getColor(R.color.blue, theme)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        setupActionBarWithNavController(navController)

        //insertFirebase()
    }

    /*private fun insertFirebase() {
        try {
            val serviceAccount = FileInputStream("paymentmethodsht-firebase-adminsdk-7r968-b0e1a1388b.json")

            val options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()

            FirebaseApp.initializeApp(options)
            println("Firebase app has been initialized successfully")
        } catch (e: IOException) {
            e.printStackTrace()
            println("Error initializing Firebase app: ${e.message}")
        }

    }*/

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}