package com.wbl.app.appifywp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.wbl.app.appifywp.MainApplication;
import com.wbl.app.appifywp.R;
import com.wbl.app.appifywp.httpCallUtils.ApiCalls;
import com.wbl.app.appifywp.models.WpMenuChildItem;
import com.wbl.app.appifywp.models.WpMenuItem;
import com.wbl.app.appifywp.ui.HomeFragment;
import com.wbl.app.appifywp.ui.InfoFragment;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.webkit.WebView;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String URL = "";

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        navigationView.setNavigationItemSelectedListener(
                item -> {
                    Fragment fragment;

                    int id = item.getItemId();

                    if (id == R.id.info_menu_item) {
                        fragment = new InfoFragment();
                    }
                    else {
                        String title = item.getTitle().toString();
                        String url = ((MainApplication) getApplication()).getMenuKeyValue().get(title);
                        fragment = new HomeFragment();
                        Bundle args = new Bundle();
                        args.putString("url", url);
                        fragment.setArguments(args);
                    }
                    //Deseleziono gli elementi selezionati
                    int size = navigationView.getMenu().size();
                    for (int i = 0; i < size; i++) {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }

                    //Checko l'elemento selezionato, aggiorno il fragment e chiudo
                    item.setChecked(true);
                    if (fragment != null) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.nav_host_fragment, fragment);
                        ft.commit();
                    }
                    drawer.closeDrawer((GravityCompat.START));

                    return true;
                }
        );

        addMenuItemInNavMenuDrawer();

        //Check Intent
        Intent intent = getIntent();
        if(intent != null) {
            Uri uri = intent.getData();
            if (uri != null) {
                String url = uri.toString();
                Fragment fragment = new HomeFragment();
                Bundle args = new Bundle();
                args.putString("url", url);
                fragment.setArguments(args);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //setIntent(intent);
        if(intent != null) {
            Uri uri = intent.getData();
            if (uri != null) {
                String url = uri.toString();
                Fragment fragment = new HomeFragment();
                Bundle args = new Bundle();
                args.putString("url", url);
                fragment.setArguments(args);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, fragment);
                ft.commit();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed()
    {
        WebView webView = findViewById(R.id.webViewMain);
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }


    private void addMenuItemInNavMenuDrawer() {

        new Thread(() -> {
            WpMenuItem myMenu;
            try {
                myMenu = ApiCalls.callGetMenu(getString(R.string.wp_site_url), getString(R.string.wp_main_menu));
                buildAppMenu(myMenu);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void buildAppMenu(WpMenuItem myMenu)
    {
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        HashMap<String, String> menuKeyValue = new HashMap<>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (WpMenuChildItem p : myMenu.getItems()) {
                    String title = p.getTitle();
                    String url = p.getUrl();
                    if(url.startsWith("/")) {
                        url = url.replace("/", "");
                        url = getString(R.string.wp_site_url) + url;
                    }
                    if(url.startsWith("#"))
                        url = getString(R.string.wp_site_url) + url;
                    menu.add(title);
                    menuKeyValue.put(title, url);
                }
                ((MainApplication) getApplication()).setMenuKeyValue(menuKeyValue);

                menu.add(Menu.NONE, R.id.info_menu_item, Menu.NONE, "Info");

                navView.invalidate();
                navView.getMenu().getItem(0).setChecked(true);
            }
        });
    }
}
