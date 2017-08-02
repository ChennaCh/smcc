package com.smcc.application.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.smcc.application.Activity.Vision;
import com.smcc.application.R;

import static com.smcc.application.UserLogins.WelcomeGuest.navItemIndex;


public class Aboutus extends Fragment {
  RelativeLayout rl;
    private String[] activityTitles;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_aboutus, container, false);
        rl =  (RelativeLayout) view.findViewById(R.id.rl);
//        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
//        toolbar.setTitle("About us");
//        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("About us");
//        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Vision.class);
                view.getContext().startActivity(intent);
            }
        });
        return  view;

    }
}
