package com.smcc.application.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.smcc.application.Activity.BoardOfStudies;
import com.smcc.application.Activity.BtechPrograms;
import com.smcc.application.Activity.Examinations;
import com.smcc.application.Activity.MtechPrograms;
import com.smcc.application.Activity.ProfessionalSocieties;
import com.smcc.application.Activity.Research;
import com.smcc.application.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Acadamics extends Fragment {
    RelativeLayout btech,mtech,bos,examinatios,ps,research;

    public Acadamics() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_acadamics, container, false);
        btech=(RelativeLayout)view.findViewById(R.id.rbtech);
        mtech=(RelativeLayout)view.findViewById(R.id.rmtech);
        bos=(RelativeLayout)view.findViewById(R.id.rbos);
        examinatios=(RelativeLayout)view.findViewById(R.id.rexaminations);
        ps=(RelativeLayout)view.findViewById(R.id.rps);
        research=(RelativeLayout)view.findViewById(R.id.rresearch);
      btech.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(getContext(), BtechPrograms.class);
              getContext().startActivity(intent);
          }
      });
        mtech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MtechPrograms.class);
                getContext().startActivity(intent);
            }
        });
        bos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BoardOfStudies.class);
                getContext().startActivity(intent);
            }
        });
        examinatios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Examinations.class);
                getContext().startActivity(intent);
            }
        });
        ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfessionalSocieties.class);
                getContext().startActivity(intent);
            }
        });
        research.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Research.class);
                getContext().startActivity(intent);
            }
        });



        return view;
    }

}
