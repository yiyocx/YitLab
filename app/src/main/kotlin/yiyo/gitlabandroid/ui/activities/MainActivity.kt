package yiyo.gitlabandroid.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.activity_main.drawer_layout
import kotlinx.android.synthetic.activity_main.tab_layout
import yiyo.gitlabandroid.R
import yiyo.gitlabandroid.ui.fragments.HomeFragment
import yiyo.gitlabandroid.ui.fragments.NavigationViewFragment
import yiyo.gitlabandroid.utils.Configuration
import yiyo.gitlabandroid.utils.toast
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), NavigationViewFragment.NavigationDrawerCallbacks {

    private val mNavigationViewFragment by Delegates.lazy {
        getFragmentManager().findFragmentById(R.id.navigation_fragment) as NavigationViewFragment }
    private val configuration: Configuration by Delegates.lazy { Configuration(this@MainActivity) }
    private var mToolbar: Toolbar by Delegates.notNull()
    private var mCurrentFragment: Fragment by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar = findViewById(R.id.toolbar) as Toolbar

        if (!configuration.isLoggedIn()) {
            logoutUser()
        }

        setupToolbar()
        setupNavigationView()
    }

    private fun setupToolbar() {
        setSupportActionBar(mToolbar)

        // Show menu icon
        val ab = getSupportActionBar()
        ab.setHomeAsUpIndicator(R.drawable.ic_menu)
        ab.setDisplayHomeAsUpEnabled(true)
    }

    fun setupNavigationView() {
        mNavigationViewFragment.setUp(R.id.navigation_fragment, drawer_layout)
    }

    private fun logoutUser() {
        configuration.closeSession()
        val intent = Intent(this, javaClass<LoginActivity>())
        startActivity(intent)
        finish()
    }

    /**
     * Para que al presionar el boton back el drawer se oculte
     */
    override fun onBackPressed() {
        if (mNavigationViewFragment.isDrawerOpen()) {
            mNavigationViewFragment.closeDrawer()
        } else {
            super<AppCompatActivity>.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                mNavigationViewFragment.openDrawer()
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
                mCurrentFragment = HomeFragment(tab_layout)
                setTitle(R.string.app_name)
            }
            R.id.navigation_item_2, R.id.navigation_item_3 -> toast(menuItem.getTitle())
            else -> mCurrentFragment = HomeFragment(tab_layout)
        }

        val fragmentManager = getSupportFragmentManager()
        fragmentManager.beginTransaction().replace(R.id.main_content, mCurrentFragment).commit()
    }
}
