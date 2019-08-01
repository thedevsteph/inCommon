package yass.stephanie.com.incommon

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import yass.stephanie.com.incommon.Home.FavouriteFragment
import yass.stephanie.com.incommon.Home.HomeFragment
import yass.stephanie.com.incommon.Home.MessagesFragment
import yass.stephanie.com.incommon.Home.ProfileFragment


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation_menu.setOnNavigationItemSelectedListener(this)

        loadFragment(HomeFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null

        when (item.itemId) {
            R.id.menu_home -> fragment = HomeFragment()

            R.id.menu_profile -> fragment = ProfileFragment()

            R.id.menu_messages -> fragment = MessagesFragment()

            R.id.menu_favourites -> fragment = FavouriteFragment()
        }

        return loadFragment(fragment)
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_activity_layout, fragment)
                .commit()
            return true
        }
        return false
    }


}
