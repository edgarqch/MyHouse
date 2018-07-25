package com.example.edgar.myhouse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.edgar.myhouse.ListDataAdapter.CustomAdapter;
import com.example.edgar.myhouse.ListDataAdapter.ItemList;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener {

    private ListView LIST;
    private ArrayList<ItemList> LISTINFO;
    private Context root;
    private CustomAdapter ADAPTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        LISTINFO = new ArrayList<ItemList>();
        root = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadComponets();
    }

    private void loadInitialRestData(String cadena) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://www.omdbapi.com/?s=" + cadena + "&page=1&apikey=e1c80c83", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray list = (JSONArray) response.get("Search");
                    LISTINFO.clear();
                    for(int i = 0; i < list.length(); i++){
                        JSONObject itemJson = list.getJSONObject(i);
                        String facade = itemJson.getString("Poster");
                        String state = itemJson.getString("Title");
                        String ubication = itemJson.getString("Type");
                        String characteristics = itemJson.getString("imdbID");
                        String price = itemJson.getString("Year");

                        ItemList item = new ItemList(facade, state, ubication, characteristics, price);
                        LISTINFO.add(item);
                    }
                    //FIX algun dia debemos arreglarlo usando eventos
                    ADAPTER = new CustomAdapter(root, LISTINFO);
                    LIST.setAdapter(ADAPTER);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

            }
        });
    }

    private void loadComponets() {
        LIST = (ListView)this.findViewById(R.id.listviewlayout);


        //LISTINFO.add(new ItemList("https://images-na.ssl-images-amazon.com/images/M/MV5BYTc3ODNmNGMtM2RmNi00MjhiLTg4MDYtZGEyOTk1N2E5ZWNiXkEyXkFqcGdeQXVyMjk3NDQyMDA@._V1_SX300.jpg","En Venta", "Zona central", "3 dormitorios, 2 baños, 180.00 m2", "197.000 Sus"));
        EditText search = (EditText)this.findViewById(R.id.search_house);

        //Click event item
        LIST.setOnItemClickListener(this);


        //Para la busqueda utilizaremos EVENTOSSSSSSSSSS.
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //¿Qué pasará antes de que cambien el texto...?
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //¿Qué pasará cuando cambien el texto...?  El charSequence es el que agarra el texto.
                String cadena = charSequence.toString();
                loadInitialRestData(cadena);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //¿Qué pasará despues de que cambien el texto...?
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String idHouse = this.LISTINFO.get(i).getCharacteristics();
        Intent houseDetail = new Intent(this, HouseDetail.class);
        houseDetail.putExtra("id", idHouse);
        this.startActivity(houseDetail);
    }
}
