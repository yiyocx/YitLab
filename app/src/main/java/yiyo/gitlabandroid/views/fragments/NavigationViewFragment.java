package yiyo.gitlabandroid.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yiyo.gitlabandroid.R;

public class NavigationViewFragment extends Fragment {

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_view, container, false);
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mNavigationView = (NavigationView) getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

    }

    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(mNavigationView);
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }
}
