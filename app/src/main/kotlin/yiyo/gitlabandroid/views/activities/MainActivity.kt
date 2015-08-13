package yiyo.gitlabandroid.views.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import yiyo.gitlabandroid.views.fragments.HomeFragment
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.utils.Configuration
import yiyo.gitlabandroid.views.fragments.NavigationViewFragment
import kotlinx.android.synthetic.activity_main.*
import yiyo.gitlabandroid.utils.extension.toast

class MainActivity : AppCompatActivity(), NavigationViewFragment.NavigationDrawerCallbacks {

    private var mNavigationViewFragment: NavigationViewFragment? = null
    private var configuration: Configuration? = null
    private var mCurrentFragment: Fragment? = null
    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.toolbar) as Toolbar

        configuration = Configuration(this@MainActivity)

        if (!configuration!!.isLoggedIn()) {
            logoutUser()
        }

        setupToolbar()
        setupNavigationView()
        setupTabLayout()
    }

    private fun setupToolbar() {
        setSupportActionBar(mToolbar)

        // Show menu icon
        val ab = getSupportActionBar()
        ab.setHomeAsUpIndicator(R.drawable.ic_menu)
        ab.setDisplayHomeAsUpEnabled(true)
    }

    fun setupNavigationView() {
        mNavigationViewFragment = getFragmentManager().findFragmentById(R.id.navigation_fragment) as NavigationViewFragment

        mNavigationViewFragment?.setUp(R.id.navigation_fragment, findViewById(R.id.drawer_layout) as DrawerLayout)
    }

    private fun setupTabLayout() {
        tab_layout.addTab(tab_layout.newTab().setText(R.string.projects_all))
        tab_layout.addTab(tab_layout.newTab().setText(R.string.projects_owned))
    }

    private fun logoutUser() {
        configuration!!.closeSession()
        val intent = Intent(this, javaClass<LoginActivity>())
        startActivity(intent)
        finish()
    }

    /**
     * Para que al presionar el boton back el drawer se oculte
     */
    override fun onBackPressed() {
        if (mNavigationViewFragment!!.isDrawerOpen()) {
            mNavigationViewFragment!!.closeDrawer()
        } else {
            super<AppCompatActivity>.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.getItemId()) {
            android.R.id.home -> {
                mNavigationViewFragment!!.openDrawer()
                true
            }
            R.id.action_logout -> {
                logoutUser()
                true
            }
            else -> super<AppCompatActivity>.onOptionsItemSelected(item)
        }
    }

    /** Pasando la opción del menú elegida para mostrar el Fragment Correspondiente  */
    override fun onNavigationDrawerItemSelected(menuItem: MenuItem) {
        setTitle(menuItem.getTitle())

        // Actualizar el contenido principal reemplazando los fragments
        when (menuItem.getItemId()) {
            R.id.navigation_item_1 -> {
                mCurrentFragment = HomeFragment()
                setTitle(R.string.app_name)
            }
            R.id.navigation_item_2, R.id.navigation_item_3 -> toast(menuItem.getTitle())
            else -> mCurrentFragment = HomeFragment()
        }

        val fragmentManager = getSupportFragmentManager()
        fragmentManager.beginTransaction().replace(R.id.main_content, mCurrentFragment).commit()
    }
}
