package com.eru.les3_m4

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.eru.les3_m4.databinding.ActivityMainBinding
import com.eru.les3_m4.ui.Prefs
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.profileFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        if (Firebase.auth.currentUser == null)
            navController.navigate(R.id.loginFragment)

        val prefs = Prefs(this)
//        if (!prefs.isShown())
            navController.navigate(R.id.boardFragment)

        navController.addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                if (destination.id == R.id.boardFragment){
                    navView.visibility = View.GONE
                    supportActionBar?.hide()
                } else{
                    navView.visibility = View.VISIBLE
                    supportActionBar?.show()
                }
            }

        })
    }
}