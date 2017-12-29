package eu.jnksoftware.discountfinderandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import eu.jnksoftware.discountfinderandroid.Utilities.ManageSharePrefs;
import eu.jnksoftware.discountfinderandroid.models.token.User;
import eu.jnksoftware.discountfinderandroid.services.GeoLocation;
import eu.jnksoftware.discountfinderandroid.ui.customer.MenuCustomer;
import eu.jnksoftware.discountfinderandroid.ui.customer.recyclers.DiscountCustomerRecyclerList;
import eu.jnksoftware.discountfinderandroid.ui.customer.userPreferences.UserPreferenceList;
import eu.jnksoftware.discountfinderandroid.ui.general.AboutUs;
import eu.jnksoftware.discountfinderandroid.ui.general.Settings;

public class CustomerMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String auth;
    private User user;
    private GeoLocation location;
    private boolean doubleBackPress;
    private TextView navHeaderEmail;
    private TextView navHeaderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        user = ManageSharePrefs.readUser( null);
        auth = "Bearer " + user.getAccessToken();
        location = new GeoLocation(this);

        MenuCustomer customerFragment = new MenuCustomer();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.customerMenuConstraintLayout,customerFragment
                ,customerFragment.getTag())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            checkDoubleBackPress();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_menu, menu);
        navHeaderEmail = findViewById(R.id.tvNavHeaderUserEmail);
        navHeaderName = findViewById(R.id.tvNavHeaderUserName);
        setUpNavHeaderInfo(getIntent().getStringExtra("email"),"Kostas");
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            MenuCustomer customerFragment = new MenuCustomer();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.customerMenuConstraintLayout,customerFragment
                    ,customerFragment.getTag())
                    .commit();
        } else if (id == R.id.nav_offers) {
            Bundle bundle = new Bundle();
            bundle.putString("auth", auth);
            bundle.putString("lat", String.valueOf(location.getLatitude()));
            bundle.putString("lon", String.valueOf(location.getLongitude()));
            DiscountCustomerRecyclerList discountsFragment = new DiscountCustomerRecyclerList();
            discountsFragment.setArguments(bundle);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.customerMenuConstraintLayout,discountsFragment
                    ,discountsFragment.getTag())
                    .commit();

        } else if (id == R.id.nav_preferences) {
            Bundle bundle = new Bundle();
            bundle.putString("auth", auth);
            UserPreferenceList preferenceFragment= new UserPreferenceList();
            preferenceFragment.setArguments(bundle);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.customerMenuConstraintLayout,preferenceFragment
                    ,preferenceFragment.getTag())
                    .commit();

        } else if (id == R.id.nav_settings) {
            Settings settingsFragment = new Settings();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.customerMenuConstraintLayout,settingsFragment
                    ,settingsFragment.getTag())
                    .commit();
        } else if(id == R.id.nav_about){
            startActivity(new Intent(getBaseContext(), AboutUs.class));
        }
          else if(id == R.id.nav_logout) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkDoubleBackPress(){
        if (doubleBackPress) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        doubleBackPress = true;

        Toast.makeText(getBaseContext(),"Please press BACK again to exit",Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                doubleBackPress = false;

            }
        }, 3000);
        doubleBackPress = true;
    }

    private void setUpNavHeaderInfo(String email,String name) {
        this.navHeaderEmail.setText(email);
        this.navHeaderName.setText(name);
    }
}
