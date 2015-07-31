package yiyo.gitlabandroid.views.fragments

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import yiyo.gitlabandroid.R

class NavigationViewFragment : Fragment() {

    private var mDrawerLayout: DrawerLayout? = null
    private var mNavigationView: NavigationView? = null

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private var mCallbacks: NavigationDrawerCallbacks? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_view, container, false)
    }

    public fun setUp(fragmentId: Int, drawerLayout: DrawerLayout) {
        mNavigationView = getActivity().findViewById(fragmentId) as NavigationView
        mDrawerLayout = drawerLayout

        mNavigationView!!.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                menuItem.setChecked(true)
                mCallbacks!!.onNavigationDrawerItemSelected(menuItem)
                closeDrawer()
                return true
            }
        })
    }

    public fun isDrawerOpen(): Boolean {
        return mDrawerLayout!!.isDrawerOpen(mNavigationView)
    }

    public fun closeDrawer() {
        mDrawerLayout!!.closeDrawers()
    }

    public fun openDrawer() {
        mDrawerLayout!!.openDrawer(GravityCompat.START)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)

        try {
            mCallbacks = activity as NavigationDrawerCallbacks?
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity must implement NavigationDrawerCallbacks")
        }

    }

    override fun onDetach() {
        super.onDetach()
        mCallbacks = null
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        fun onNavigationDrawerItemSelected(menuItem: MenuItem)
    }
}
