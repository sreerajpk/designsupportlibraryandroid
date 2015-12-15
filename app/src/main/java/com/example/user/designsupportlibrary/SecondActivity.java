package com.example.user.designsupportlibrary;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends ActionBarActivity {

    private CoordinatorLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setLogo(R.mipmap.ic_launcher);

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.main_content);
        List<String> titleList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            titleList.add("Tab " + (i + 1));
        }


        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 4"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 5"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 6"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 7"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 8"));
        //tabLayout.setupWithViewPager(pager);
        populateListView();
    }

    private void populateListView() {
        ListView listView = (ListView) findViewById(R.id.listView);

        Conversation conversation;
        List<Conversation> conversationList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            conversation = new Conversation();
            conversation.setName("QBurst " + i);
            conversation.setLastMessage("Hi, this is a text message to check the listview");
            conversationList.add(conversation);
        }
        final Adapter adapter = new Adapter(this, conversationList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.removeConversation(i);
          /*  Snackbar
                    .make(linearLayout, "Conversation deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(MainActivity.this, "Toast", Toast.LENGTH_SHORT).show();
                            adapter.addRemovedConversationAgain();
                        }
                    })
                    .setActionTextColor(getResources().getColor(R.color.material_deep_teal_500))
                    .show();*/

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Conversation deleted", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        adapter.addRemovedConversationAgain();
                    }
                })
                        .setActionTextColor(getResources().getColor(R.color.material_deep_teal_500));
            /*View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));*/
                snackbar.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
}
