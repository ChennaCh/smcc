package com.smcc.application.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.smcc.application.Activity.Vision;
import com.smcc.application.R;


public class Aboutus extends Fragment {
    CardView card_view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_aboutus, container, false);
        card_view =  (CardView) view.findViewById(R.id.card_view);
        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(),Vision.class);
//                view.getContext().startActivity(intent);

            }
        });
        return  view;

    }

//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(getActivity(),Vision.class);
//        getActivity().startActivity(intent);
//    }
}
