package com.smcc.application.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smcc.application.Activity.CollegeMap;
import com.smcc.application.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUs extends Fragment {
    ImageView collegemap;
    public ContactUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contactus, container, false);
        collegemap=(ImageView)view.findViewById(R.id.smecmap);
        collegemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), CollegeMap.class);
                getContext().startActivity(intent);
            }
        });
        return view;
    }

}
