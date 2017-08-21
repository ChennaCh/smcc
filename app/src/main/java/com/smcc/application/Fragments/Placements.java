package com.smcc.application.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.smcc.application.PlacementsActivities.CampusSelections;
import com.smcc.application.PlacementsActivities.CompaniesVisited;
import com.smcc.application.PlacementsActivities.PlacementsContactUs;
import com.smcc.application.PlacementsActivities.PlacementsHome;
import com.smcc.application.R;

public class Placements extends Fragment {
    RelativeLayout phome,pcampus,pplaced,pcmpnyvisited,pcontactus;
    public Placements() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_placements, container, false);
        phome=(RelativeLayout)v.findViewById(R.id.rplcmthome);
        pcampus=(RelativeLayout)v.findViewById(R.id.rcampus);
        pcmpnyvisited=(RelativeLayout)v.findViewById(R.id.rcmpnyvisit);
        pcontactus=(RelativeLayout)v.findViewById(R.id.rcontactus);

        phome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlacementsHome.class);
                getContext().startActivity(intent);
            }
        });
        pcampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CampusSelections.class);
                getContext().startActivity(intent);
            }
        });
        pcmpnyvisited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CompaniesVisited.class);
                getContext().startActivity(intent);
            }
        });
        pcontactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlacementsContactUs.class);
                getContext().startActivity(intent);
            }
        });

        return v;

    }


}
