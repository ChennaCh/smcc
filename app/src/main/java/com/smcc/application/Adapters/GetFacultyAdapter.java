package com.smcc.application.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.smcc.application.Bean.GetFacultyBean;
import com.smcc.application.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by chinn on 8/4/2017.
 */

public class GetFacultyAdapter extends BaseAdapter {

    ArrayList<GetFacultyBean> adapterItems;
    Context context;
    Activity activity;

    public GetFacultyAdapter(Context cotext1,ArrayList<GetFacultyBean> facultybean){
        this.context = cotext1;
        this.adapterItems = facultybean;
    }


    @Override
    public int getCount() {
        return adapterItems.size();
    }

    @Override
    public Object getItem(int position) {
        return adapterItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return adapterItems.indexOf(position);
    }

    @Override
    public View getView(int postion, View view, ViewGroup parent) {

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.view_faculty_details, null);

        ViewHolder holder;
        holder = new ViewHolder();

        holder.name = (TextView) view.findViewById(R.id.facu_name);
        holder.branch = (TextView) view.findViewById(R.id.facu_branch);
        holder.mobile = (TextView) view.findViewById(R.id.facu_mobile);
        holder.qualificaion = (TextView) view.findViewById(R.id.facu_qualification);
        holder.getimage = (ImageView) view.findViewById(R.id.get_image);

        if (adapterItems.get(postion).getGname() != null){
            holder.name.setText(adapterItems.get(postion).getGname());
        }
        if (adapterItems.get(postion).getGmobile() != null){
            holder.mobile.setText(adapterItems.get(postion).getGmobile());
        }
        if (adapterItems.get(postion).getGbranch() != null){
            holder.branch.setText(adapterItems.get(postion).getGbranch());
        }
        if (adapterItems.get(postion).getGqualification() != null){
            holder.qualificaion.setText(adapterItems.get(postion).getGqualification());
        }
        if (adapterItems.get(postion).getGgender() != null){
            if (adapterItems.get(postion).toString().equals("MALE")){
                holder.getimage.setImageResource(R.drawable.men);
            }
            else {
                holder.getimage.setImageResource(R.drawable.women);
            }
        }


        return view;
    }

    private class ViewHolder {
        TextView name, mobile, branch,qualificaion;
        ImageView getimage;
    }
}
