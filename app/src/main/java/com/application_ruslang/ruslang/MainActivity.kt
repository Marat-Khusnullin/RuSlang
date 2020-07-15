package com.application_ruslang.ruslang

import android.annotation.SuppressLint
import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.application_ruslang.ruslang.view.InfoFragment
import com.application_ruslang.ruslang.view.PopularFragment
import com.application_ruslang.ruslang.view.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var bottomNavigationView: BottomNavigationView? = null
    val SEARCH_FRAGMENT_TAG = "SEARCH_FRAGMENT_TAG"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                      var a =  PopularFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container, a)
                            .commit()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_favorites -> {
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
        bottomNavigationView?.selectedItemId = R.id.action_search

    }


}
