package yiyo.gitlabandroid.views.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import yiyo.gitlabandroid.R;
import yiyo.gitlabandroid.views.fragments.NavigationViewFragment;

public class MainActivity extends AppCompatActivity {

    private NavigationViewFragment mNavigationViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initializeNavigationView();
    }

    public void initializeNavigationView() {
        mNavigationViewFragment = (NavigationViewFragment) getFragmentManager()
                .findFragmentById(R.id.navigation_fragment);

        mNavigationViewFragment.setUp(R.id.navigation_fragment,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void some(MenuItem item) {

    }
}
