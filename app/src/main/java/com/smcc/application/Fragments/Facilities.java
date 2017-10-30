package com.smcc.application.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smcc.application.Activity.FacultyViewFeedback;
import com.smcc.application.Activity.ViewFacultyGuest;
import com.smcc.application.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Facilities extends Fragment {

    private Button cse,ece;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_facilities, container, false);
        cse = (Button) view.findViewById(R.id.guest_cse);
        ece = (Button) view.findViewById(R.id.guest_ece);
        //eee = (Button) view.findViewById(R.id.guest_eee);

        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cse = "cse";
                Intent intent = new Intent(getContext(), ViewFacultyGuest.class);
                intent.putExtra("branch",cse);
                startActivity(intent);
            }
        });

        ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ece1 = "ece";
                Intent intent = new Intent(getContext(), ViewFacultyGuest.class);
                intent.putExtra("branch",ece1);
                startActivity(intent);
            }
        });

//        eee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String eee1 = "eee";
//                Intent intent = new Intent(getContext(), ViewFacultyGuest.class);
//                intent.putExtra("branch",eee1);
//                startActivity(intent);
//            }
//        });
        return view;
    }

}
