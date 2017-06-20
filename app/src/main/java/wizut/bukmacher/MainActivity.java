package wizut.bukmacher;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView lv_main;
    TextView tv_name, tv_login;
    List arrayMain;
    List arrayId;
    ArrayAdapter arrayAdapter;
    Context context;
    String PREFS_NAME = "MyPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("first_launch", true)) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

        context = getBaseContext();

        arrayMain = new ArrayList<String>();
        arrayId = new ArrayList<>();
        /*arrayMain.add("Football");
        arrayMain.add("Volleyball");
        arrayMain.add("Handball");
        arrayMain.add("Basketball");
        arrayMain.add("Tennis");
        arrayMain.add("Hockey");
        arrayMain.add("Baseball");
        arrayMain.add("Winter sports");*/

        arrayAdapter = new ArrayAdapter(this, R.layout.list_element, R.id.lv_element, arrayMain);
        lv_main = (ListView) findViewById(R.id.lv_main);
        lv_main.setAdapter(arrayAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        tv_name = (TextView) header.findViewById(R.id.tv_name);
        tv_login = (TextView) header.findViewById(R.id.tv_login);
        tv_name.setText(settings.getString("name", "Imię i ") + " " + settings.getString("surname", "Imię"));
        tv_login.setText(settings.getString("login", "Login"));

        //Uruchamianie serwisu
        Intent intent = new Intent(this, HttpService.class);
        PendingIntent pendingResult = createPendingResult(0, new Intent(),0);
        intent.putExtra(HttpService.RETURN, pendingResult);
        startService(intent);

        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ListOfMatchActivity.class);
                intent.putExtra(HttpService.ID, arrayId.get(position).toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            JSONArray jsonArray = new JSONArray(data.getStringExtra(HttpService.RESPONSE));
            for(int i=0; i < jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                arrayMain.add(jsonObject.optString("caption"));
                arrayId.add(jsonObject.optString("id"));
            }

            arrayAdapter.notifyDataSetChanged();

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            Toast.makeText(getApplicationContext(), "Refreshing...", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_bets) {
            //Intent intent = new Intent(getApplicationContext(), ActivityMeczu.class);
           // startActivity(intent);
        } else if (id == R.id.nav_my_bets) {

        } else if (id == R.id.nav_my_account) {

        } else if (id == R.id.nav_my_stats) {

        } else if (id == R.id.nav_settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
