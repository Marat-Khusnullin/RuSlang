package com.application_ruslang.ruslang.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.application_ruslang.ruslang.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var bottomNavigationView: BottomNavigationView? = null
    val SEARCH_FRAGMENT_TAG = "SEARCH_FRAGMENT_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow()?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.action_search -> {
                        var searchFragment: Fragment? =
                            supportFragmentManager.findFragmentByTag(SEARCH_FRAGMENT_TAG)
                        if (searchFragment == null) {
                            searchFragment = SearchFragment(this)
                        }
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, searchFragment, SEARCH_FRAGMENT_TAG)
                            .commit()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_rating -> {
                        supportFragmentManager.beginTransaction()
                            .add(R.id.container, PopularFragment())
                            .commit()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_favorites -> {
                        supportFragmentManager.beginTransaction()
                            .add(R.id.container, FavoritesFragment())
                            .commit()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_info -> {
                        supportFragmentManager.beginTransaction()
                            .add(R.id.container, InfoFragment()).commit()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

        bottomNavigationView?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavigationView?.selectedItemId =
            R.id.action_search

    }

    /*override fun onBackPressed() {
        //super.onBackPressed()
        var fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment != null) {
            supportFragmentManager.beginTransaction().remove(fragment).setCustomAnimations(R.animator.ffrmnt_nmtr, R.animator.fragment_remove).commit()
        }*/


}



