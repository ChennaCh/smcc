package com.smcc.application.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.smcc.application.AboutUsActivities.AdminHrPolicy;
import com.smcc.application.AboutUsActivities.Management;
import com.smcc.application.AboutUsActivities.Mou;
import com.smcc.application.AboutUsActivities.Philosophy;
import com.smcc.application.AboutUsActivities.Principal;
import com.smcc.application.AboutUsActivities.Vision;
import com.smcc.application.R;
import com.smcc.application.UserLogins.WelcomeGuest;


public class Aboutus extends Fragment {
  RelativeLayout aboutus,principal,mngt,phylosophy,administration,mou;
    private String[] activityTitles;
    WelcomeGuest wg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_aboutus, container, false);
        aboutus =  (RelativeLayout) view.findViewById(R.id.rl);
        principal=(RelativeLayout)view.findViewById(R.id.rprincipal);
        mngt=(RelativeLayout)view.findViewById(R.id.rmngt);
        phylosophy=(RelativeLayout)view.findViewById(R.id.rphilosophy);
        administration=(RelativeLayout)view.findViewById(R.id.rhr);
        mou=(RelativeLayout)view.findViewById(R.id.rmou);
       // getActivity().setTitle("About Us");
//        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
//        toolbar.setTitle("About us");
//        ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle("About us");
//        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Vision.class);
                view.getContext().startActivity(intent);
            }
        });
        principal.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                              Intent i=new Intent(view.getContext(), Principal.class);
                                                view.getContext().startActivity(i);
                                            }
                                        }
        );
        mngt.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent i=new Intent(view.getContext(), Management.class);
                                             view.getContext().startActivity(i);
                                         }
                                     }
        );
        phylosophy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(), Philosophy.class);
                view.getContext().startActivity(i);
            }
        });
        administration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(), AdminHrPolicy.class);
                view.getContext().startActivity(i);
            }
        });
        mou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(),Mou.class);
                view.getContext().startActivity(i);
            }
        });
        return  view;

    }
}
