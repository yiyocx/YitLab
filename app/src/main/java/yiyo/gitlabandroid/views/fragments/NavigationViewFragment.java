package yiyo.gitlabandroid.views.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import yiyo.gitlabandroid.R;

public class NavigationViewFragment extends Fragment {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_view, container, false);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mNavigationView = (NavigationView) getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mCallbacks.onNavigationDrawerItemSelected(menuItem);
                NavigationViewFragment.this.closeDrawer();
                return true;
            }
        });
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(mNavigationView);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    public void openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(MenuItem menuItem);
    }
}
