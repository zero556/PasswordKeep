package com.passwordkeep.zeromq.passwordkeep.activites.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.utils.AesUtils;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;

import java.util.ArrayList;

/**
 * Created by VMLDEV6 on 09/10/2017.
 */

public class PasswordShowDetailAdapter extends BaseAdapter {

    private ArrayList<String[]> arrayList=null;
    private Context pContext;
    private String groupKey ="title, select, content, remark, picture" ;
    Gson gs =new Gson();
    public enum groupkey {

        title, type, content, remark, picture

    }
    public PasswordShowDetailAdapter( ArrayList<String[]> arrayList,Context pContext)
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

        if(arrayList.get(position)[0]== PasswordCreateAdapter.groupkey.type.toString())   {
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
            textVeiwIcon.setText(null);
            textVeiwIcon.setTextSize(20);
            textVeiwIcon.setTypeface(font);


        }
        else if (arrayList.get(position)[0]== PasswordCreateAdapter.groupkey.content.toString()){
            view=  LayoutInflater.from(pContext).inflate(R.layout.add_content_list_item_tag,parent,false);
            PasswordKeepModel jsonObject = gs.fromJson(arrayList.get(position)[1], PasswordKeepModel.class);

            EditText editVeiwTitle=(EditText) view.findViewById(R.id.createTitle);
            editVeiwTitle.setText(jsonObject.getTitle());
            setEditTextReadOnly(editVeiwTitle);

            EditText editVeiwAccount=(EditText) view.findViewById(R.id.createAccount);
            String account=  AesUtils.decrypt(jsonObject.getId(),jsonObject.getAccount());
            editVeiwAccount.setText(account);
            setEditTextReadOnly(editVeiwAccount);

            String password=  AesUtils.decrypt(jsonObject.getId(),jsonObject.getPassword());
            EditText editVeiwPassword=(EditText) view.findViewById(R.id.createPassword);
            editVeiwPassword.setText(password);
            setEditTextReadOnly(editVeiwPassword);


        }
        else if (arrayList.get(position)[0]== PasswordCreateAdapter.groupkey.remark.toString()){
            view=  LayoutInflater.from(pContext).inflate(R.layout.add_remark_list_item_tag,parent,false);


            PasswordKeepModel jsonObject = gs.fromJson(arrayList.get(position)[1], PasswordKeepModel.class);

            EditText editVeiwRemark=(EditText) view.findViewById(R.id.createRemark);
            editVeiwRemark.setText(jsonObject.getRemark());
            setEditTextReadOnly(editVeiwRemark);
        }
        else if(arrayList.get(position)[0]== PasswordCreateAdapter.groupkey.picture.toString())
        {
            view=  LayoutInflater.from(pContext).inflate(R.layout.add_select_list_item_tag,parent,false);

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


    public static void setEditTextReadOnly(TextView view){

        if (view instanceof android.widget.EditText){
            view.setCursorVisible(false);      //设置输入框中的光标不可见
            view.setFocusable(false);           //无焦点
            view.setFocusableInTouchMode(false);     //触摸时也得不到焦点
        }
    }
}
