package com.passwordkeep.zeromq.passwordkeep.activites.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.SimpleSwipeListener;
import com.passwordkeep.zeromq.passwordkeep.activites.SwipeLayout;
import com.passwordkeep.zeromq.passwordkeep.activites.utils.AesUtils;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Created by VMLDEV6 on 22/09/2017.
 */

public class PasswordShowListAdapter extends BaseAdapter {
    private LinkedList<PasswordKeepModel> pData;
    private Context pContext;
    private SwipeLayout swipeLayoutView;
    // private String  pageType;

    public PasswordShowListAdapter(LinkedList<PasswordKeepModel> pData, Context pContext)
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

    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public View getView(int position, View converView, ViewGroup parent)
    {


        Typeface font = Typeface.createFromAsset(pContext.getAssets(), "fontawesome-webfont.ttf");

        converView= LayoutInflater.from(pContext).inflate(R.layout.list_show_item,parent,false);
        swipeLayoutView= ((SwipeLayout) converView);
        //swipeLayoutView.setShowMode(SwipeLayout.ShowMode.PullOut);
        //((SwipeLayout) converView).addDrag(SwipeLayout.DragEdge.Left, swipeLayoutView.findViewById(R.id.bottom_wrapper));


        ((SwipeLayout) converView).addDrag(SwipeLayout.DragEdge.Right, swipeLayoutView.findViewById(R.id.bottom_wrapper_2));


        //TextView textDelete=(TextView)converView.findViewById(R.id.delete);
        TextView textrash=(TextView)converView.findViewById(R.id.trash2);
        TextView textType=(TextView)converView.findViewById(R.id.textViewType);
        TextView textTitle=(TextView)converView.findViewById(R.id.textViewTitle);
        TextView textAccount=(TextView)converView.findViewById(R.id.textViewAccount);
        TextView textPassword=(TextView)converView.findViewById(R.id.textViewPassword);
        TextView textId=(TextView)converView.findViewById(R.id.textViewId);

        textType.setText(pData.get(position).getPasswordType().getIcon());
        textType.setTextSize(25);
        textTitle.setText(pData.get(position).getTitle());
        textAccount.setText(AesUtils.decrypt(pData.get(position).getId(),pData.get(position).getAccount()));
        //textPassword.setText("******");
        textId.setText(pData.get(position).getId());


        textType.setTypeface(font);
        //textDelete.setTypeface(font);
        textrash.setTypeface(font);

        return converView;

    }
}
