package com.example.user.designsupportlibrary;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private DrawerLayout drawerLayout;
    private CoordinatorLayout coordinatorLayout;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setLogo(R.mipmap.ic_launcher);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_content);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        final NavigationView view = (NavigationView) findViewById(R.id.navigation_view);
        view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                prevMenuItem = menuItem;
                /*return true;*/


                /*menuItem.setChecked(true);
                drawerLayout.closeDrawers();*/
                Snackbar
                        .make(coordinatorLayout, "Option Selected", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Toast.makeText(MainActivity.this, "Toast", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.material_deep_teal_500))
                        .show();
                return true;
            }
        });

        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            titleList.add("Tab " + (i + 1));
        }

        ViewPager pager = (ViewPager)
                findViewById(R.id.viewpager);
        pager.setAdapter(new ViewPagerAdapter(this.getSupportFragmentManager(), titleList, 8));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(pager);
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

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
