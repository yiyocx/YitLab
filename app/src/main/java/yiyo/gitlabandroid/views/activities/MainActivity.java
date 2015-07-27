package yiyo.gitlabandroid.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import yiyo.gitlabandroid.R;
import yiyo.gitlabandroid.utils.Configuration;
import yiyo.gitlabandroid.views.fragments.NavigationViewFragment;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    private NavigationViewFragment mNavigationViewFragment;
    private Configuration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configuration = new Configuration(MainActivity.this);

        if (!configuration.isLoggedIn()) {
            logoutUser();
        }

        ButterKnife.bind(this);
        setupToolbar();
        initializeNavigationView();
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);

        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void initializeNavigationView() {
        mNavigationViewFragment = (NavigationViewFragment) getFragmentManager()
                .findFragmentById(R.id.navigation_fragment);

        mNavigationViewFragment.setUp(R.id.navigation_fragment,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    private void logoutUser() {
        configuration.closeSession();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Para que al presionar el boton back el drawer se oculte
     */
    @Override
    public void onBackPressed() {

        if(mNavigationViewFragment.isDrawerOpen()){
            mNavigationViewFragment.closeDrawer();
        } else {
            super.onBackPressed();
        }
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
