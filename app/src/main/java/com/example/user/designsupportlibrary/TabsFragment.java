package com.example.user.designsupportlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/7/15.
 */
public class TabsFragment extends Fragment {
    private Context context;
    private View fragmentView;
    private int tabPosition;
    boolean usernameFirstTime = true;
    boolean passwordFirstTime = true;

    public TabsFragment() {

    }

    public static Fragment newInstance(int position) {
        TabsFragment tabsFragment = new TabsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        tabsFragment.setArguments(bundle);
        return tabsFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        context = activity;
        Bundle bundle = getArguments();
        tabPosition = bundle.getInt("position");
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (tabPosition == 0) {
            fragmentView = layoutInflater.inflate(R.layout.fragment_list_view, container, false);
        } else if (tabPosition == 1) {
            fragmentView = layoutInflater.inflate(R.layout.fragment_nested_scroll_view, container, false);
        } else if (tabPosition == 2) {
            fragmentView = layoutInflater.inflate(R.layout.fragment_text_input_layout, container, false);
        } else if (tabPosition == 3) {
            fragmentView = layoutInflater.inflate(R.layout.fragment_animated_list, container, false);
        } else {
            fragmentView = layoutInflater.inflate(R.layout.fragment_others, container, false);
        }
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (tabPosition == 0) {
            populateListView(view);
        } else if (tabPosition == 1) {
            // Do nothing
        } else if (tabPosition == 2) {

            final TextInputLayout usernameText = (TextInputLayout) view.findViewById(R.id.username_text_input);
            usernameText.setError("Username cant be blank");
            final TextInputLayout passwordText = (TextInputLayout) view.findViewById(R.id.password_text_input);
            passwordText.setError("Password cant be blank");

            ((Button) view.findViewById(R.id.whatsapp_button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WhatsappToolbarActivity.class);
                    startActivity(intent);
                }
            });
        } else if (tabPosition == 3) {
            ((Button) view.findViewById(R.id.startAnimationButton)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView image = (ImageView) fragmentView.findViewById(R.id.image);
                    image.setBackgroundResource(R.anim.animated_list);

                    AnimationDrawable rocketAnimation = (AnimationDrawable) image.getBackground();
                    rocketAnimation.start();
                }
            });
        } else {
            ((TextView) view.findViewById(R.id.textView)).setText("Tab " + (tabPosition + 1));
        }
    }

    private void populateListView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.listView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }
        Conversation conversation;
        List<Conversation> conversationList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            conversation = new Conversation();
            conversation.setName("QBurst " + i);
            conversation.setLastMessage("Hi, this is a text message to check the listview");
            conversationList.add(conversation);
        }
        final Adapter adapter = new Adapter(context, conversationList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.removeConversation(i);

                Snackbar snackbar = Snackbar
                        .make(view, "Conversation deleted", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        adapter.addRemovedConversationAgain();
                    }
                })
                        .setActionTextColor(getResources().getColor(R.color.white));
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.light_red));
                snackbar.show();
            }
        });
    }
}