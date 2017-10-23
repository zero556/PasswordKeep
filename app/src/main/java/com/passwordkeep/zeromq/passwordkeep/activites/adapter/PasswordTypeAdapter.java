package com.passwordkeep.zeromq.passwordkeep.activites.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordTypeModel;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by VMLDEV6 on 14/09/2017.
 */

public class PasswordTypeAdapter extends BaseAdapter {
    private LinkedList<PasswordTypeModel> pData;
    private Context pContext;
   // private String  pageType;

    public PasswordTypeAdapter(LinkedList<PasswordTypeModel> pData, Context pContext)
    {
        this.pContext=pContext;
        this.pData=pData;
        //this.pageType=pageType;
    }

    public int getCount()
    {
        return pData.size();
    }

    public Objects getItem(int position)
    {
        return null;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View converView, ViewGroup parent)
    {

        Typeface font = Typeface.createFromAsset(pContext.getAssets(), "fontawesome-webfont.ttf");

        converView= LayoutInflater.from(pContext).inflate(R.layout.list_item,parent,false);
        //ImageView imgIcon =(ImageView)converView.findViewById(R.id.imgTitle);
        TextView textType=(TextView)converView.findViewById(R.id.textViewType);
        TextView textTitle=(TextView)converView.findViewById(R.id.textViewTitle);
        TextView textCount=(TextView)converView.findViewById(R.id.textViewCount);

        //imgIcon.setImageResource(pData.get(position).getIcon());

        textType.setText(pData.get(position).getIcon());
        textTitle.setText(pData.get(position).getTitle());
        if(pData.get(position).getCount()>=0 && pData.get(position).getCount()<=1000){
            textCount.setText(pData.get(position).getCount()+"");
        }
        else
        {
            textCount.setText(pData.get(position).getCount());
            textCount.setTextSize(20);
            textCount.setTextColor(converView.getResources().getColor(R.color.colorIcon));
        }

        textType.setTypeface(font);
        textCount.setTypeface(font);
        return converView;

    }
}

