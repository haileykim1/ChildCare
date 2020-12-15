package com.example.childcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var policy:StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){

        loadFragment(ccFacilityFragment())

        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){

                R.id.nav_ccFacilities-> {
                    loadFragment(ccFacilityFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_protectionplay-> {
                    loadFragment(protectPlayFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_vaccination-> {
                    loadFragment(vaccinationFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_cclocation-> {
                    loadFragment(ccLocationFragment())
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false
        }
    }

    private fun loadFragment(frag: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameView, frag)
        transaction.commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_actions, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId){
            R.id.action_drawer -> {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
