package com.smcc.application.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smcc.application.Bean.GetFeedbackBean;
import com.smcc.application.R;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by chinn on 8/10/2017.
 */

public class FeedBackAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<GetFeedbackBean> data= Collections.emptyList();


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view= Inflater.inflate(R.layout.admin_view_feedback, parent,false);
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_view_feedback, parent, false);
        MyHolder holder=new MyHolder(itemView);
        return holder;
    }

    public FeedBackAdapter(Context context, List<GetFeedbackBean> data1){
        this.context=context;
        this.data=data1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        GetFeedbackBean current = data.get(position);
        myHolder.name.setText(current.getName());
        myHolder.department.setText(current.getBranch());
        myHolder.phone.setText(current.getMobile());
        myHolder.email.setText(current.getEmail());
        myHolder.feedback.setText("      "+current.getMsg());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class MyHolder extends RecyclerView.ViewHolder{

        TextView email,phone,department,name,feedback;

        public MyHolder(View itemView) {
            super(itemView);
            email = (TextView) itemView.findViewById(R.id.get_mail);
            phone = (TextView) itemView.findViewById(R.id.get_phone);
            department = (TextView) itemView.findViewById(R.id.get_department);
            name = (TextView) itemView.findViewById(R.id.get_name);
            feedback = (TextView) itemView.findViewById(R.id.get_feedback);
        }
    }
}
