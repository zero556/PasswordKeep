package com.passwordkeep.zeromq.passwordkeep.activites.adapter;

/**
 * Created by VMLDEV6 on 19/09/2017.
 */
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.passwordkeep.zeromq.passwordkeep.R;

import java.util.ArrayList;

public class PasswordCreateAdapter extends BaseAdapter{


    private ArrayList<String[]> arrayList=null;
    private Context pContext;
    private String groupKey ="title, select, content, remark, picture" ;
    public enum groupkey {

       title, type, content, remark, picture

    }
    public PasswordCreateAdapter( ArrayList<String[]> arrayList,Context pContext)
    {
        this.pContext=pContext;
        this.arrayList=arrayList;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        if(groupKey.contains(getItem(position).toString())){
            return false;
        }
        return super.isEnabled(position);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view=convertView;

        Typeface font = Typeface.createFromAsset(pContext.getAssets(), "fontawesome-webfont.ttf");

        if(arrayList.get(position)[0]==groupkey.type.toString())   {
            view=  LayoutInflater.from(pContext).inflate(R.layout.add_select_list_item_tag,parent,false);
//            ImageView image=(ImageView) view.findViewById(R.id.img);
//            image.setImageResource(R.mipmap.monitor);
            int iconIndex= Integer.parseInt(arrayList.get(position)[2]);

            TextView textVeiwImage=(TextView) view.findViewById(R.id.img);
            textVeiwImage.setText(iconIndex);
            textVeiwImage.setTypeface(font);

            TextView textVeiw=(TextView) view.findViewById(R.id.textView);
            textVeiw.setText(arrayList.get(position)[1].toString());

            TextView textVeiwIcon=(TextView) view.findViewById(R.id.textViewIcon);
            textVeiwIcon.setText(R.string.fa_chevron_circle_right );
            textVeiwIcon.setTextSize(20);
            textVeiwIcon.setTypeface(font);


        }
        else if (arrayList.get(position)[0]==groupkey.content.toString()){
            view=  LayoutInflater.from(pContext).inflate(R.layout.add_content_list_item_tag,parent,false);

        }
        else if (arrayList.get(position)[0]==groupkey.remark.toString()){
            view=  LayoutInflater.from(pContext).inflate(R.layout.add_remark_list_item_tag,parent,false);

        }
        else if(arrayList.get(position)[0]==groupkey.picture.toString())
        {
            view=  LayoutInflater.from(pContext).inflate(R.layout.add_select_list_item_tag,parent,false);
//            ImageView image=(ImageView) view.findViewById(R.id.img);
//            image.setImageResource(R.mipmap.camera2);
//            image.setBackgroundResource(0);
//
//            ViewGroup.LayoutParams para =(ViewGroup.LayoutParams)image.getLayoutParams();
//            para.height = 70;
//            para.width = 70;
//            image.setLayoutParams(para);

            TextView textVeiwImage=(TextView) view.findViewById(R.id.img);
            textVeiwImage.setText(R.string.fa_camera);
            textVeiwImage.setTypeface(font);

            TextView textVeiw=(TextView) view.findViewById(R.id.textView);
            textVeiw.setText(arrayList.get(position)[1].toString());

            TextView textVeiwIcon=(TextView) view.findViewById(R.id.textViewIcon);
            textVeiwIcon.setTypeface(font);

        }
        else
        {
            view=  LayoutInflater.from(pContext).inflate(R.layout.add_title_list_item_tag,parent,false);
        }

        TextView text=(TextView) view.findViewById(R.id.add_title_list_item_text);
        if(text!=null) {
            text.setText(arrayList.get(position)[1].toString());
        }
        return view;
    }

}
