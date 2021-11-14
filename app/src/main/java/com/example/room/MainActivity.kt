package com.example.room

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,NavigationView.OnNavigationItemSelectedListener{

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

         window.decorView.layoutDirection=View.LAYOUT_DIRECTION_RTL // to view menu from right

        if(savedInstanceState==null) // to  view the home fragment in  beginning
        {
            supportFragmentManager.beginTransaction().replace(R.id.home_frame,homeFragment()).commitNow()
        }


        val toggle = ActionBarDrawerToggle(// to view ||| of menu
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        //val navController = findNavController(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //setupActionBarWithNavController(navController, appBarConfiguration)
        //navView.setupWithNavController(navController)


        nav_view.setNavigationItemSelectedListener(this) //to make item on onNavigationItemSelected make order
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {

            R.id.id_home->{
                supportFragmentManager.beginTransaction().replace(R.id.home_frame,homeFragment()).commitNow()
            }
            R.id.id_about->{
                supportFragmentManager.beginTransaction().replace(R.id.home_frame,aboutFragment()).commitNow()
            }
            R.id.about_Team->{
                supportFragmentManager.beginTransaction().replace(R.id.home_frame,teamFragment()).commitNow()
            }
            R.id.nav_requests->{
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+"rabeeadel188@gmail.com"+"?subject="+"from app")))


            }
            R.id.Share->{
                  val sent=Intent()
                sent.action=Intent.ACTION_SEND
                sent.putExtra(Intent.EXTRA_TEXT,"download app for learning c++")
                sent.type="text/plain"
                startActivity(Intent.createChooser(sent,"choose your app you want"))
            }


        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}