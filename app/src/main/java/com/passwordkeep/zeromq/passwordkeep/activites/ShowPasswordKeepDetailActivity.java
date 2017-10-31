package com.passwordkeep.zeromq.passwordkeep.activites;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.adapter.PasswordShowDetailAdapter;
import com.passwordkeep.zeromq.passwordkeep.activites.model.CustomDialog;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;
import com.passwordkeep.zeromq.passwordkeep.activites.utils.SaveObjectUtils;
import com.passwordkeep.zeromq.passwordkeep.activites.model.SingletonModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class ShowPasswordKeepDetailActivity extends TitleActivity {

    private ArrayList<String[]> itemlist=null;
    private Context pContext;
    private PasswordShowDetailAdapter psasswordShowDetailAdapter=null;
    private ListView plistView;
    private SingletonModel singletonModel;
    private String type =null;
    private int iconIndex=-1;
    PasswordKeepModel passwordKeepModel;
    SaveObjectUtils utils;
    private static final String key=LoginActivity.class.getSimpleName();
    private CustomDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_password_keep_detail);
        utils=new SaveObjectUtils(this,key);

        showForwardView(R.string.fa_remove,true);
        Button mForwardButton = (Button) findViewById(R.id.button_forward);
        mForwardButton.setTextSize(25);
        mForwardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                deleteKeep();
            }
        });
        setContentView(R.layout.activity_create);
        singletonModel= SingletonModel.getInstance();
        utils=new SaveObjectUtils(this,key);
        //Bundle bundle = this.getIntent().getExtras();
        passwordKeepModel = new PasswordKeepModel();
        Intent intent=this.getIntent();
        if(intent!=null) {
            passwordKeepModel = (PasswordKeepModel) intent.getSerializableExtra("passwordKeepModel");

        }

        Gson gs = new Gson();
        String passwordKeepModelString=gs.toJson(passwordKeepModel);//

        setTitle("");
        showBackwardView(R.string.fa_chevron_circle_left,true);
        Button mBackwardButton = (Button) findViewById(R.id.button_backward);
        mBackwardButton.setTextSize(28);
        mBackwardButton.setBackground(null);

        itemlist = new ArrayList<String[]>() ;
        String[] item={"Title","Select Type :"};
        itemlist.add(new String[]{"title","Select Type :"});
        itemlist.add(new String[]{"type",passwordKeepModel.getPasswordType().getTitle(),passwordKeepModel.getPasswordType().getIcon()+""});
        itemlist.add(new String[]{"title","Fill in Content :"});
        itemlist.add(new String[]{"content",passwordKeepModelString});
        itemlist.add(new String[]{"title","Fill in Remark :"});
        itemlist.add(new String[]{"remark",passwordKeepModelString});


        pContext=ShowPasswordKeepDetailActivity.this;
        plistView=(ListView)findViewById(R.id.listView_list);

        psasswordShowDetailAdapter=new PasswordShowDetailAdapter(itemlist,pContext);
//
        plistView.setAdapter(psasswordShowDetailAdapter);



    }


    public void deleteKeep()
    {

        CustomDialog.Builder customBuilder = new
                CustomDialog.Builder(ShowPasswordKeepDetailActivity.this);
        customBuilder.setTitle("Delete")
                .setMessage("Delete keeper ?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(passwordKeepModel!=null)
                                {
                                    List<PasswordKeepModel> passwordKeepModelList= singletonModel.getUserModel().PasswordKeepList;

                                    for(PasswordKeepModel p:passwordKeepModelList){
                                        if(p.getId().equals(passwordKeepModel.getId()))
                                        {
                                            passwordKeepModelList.remove(p);
                                            break;
                                        }

                                    }

                                    UserModel userModel=singletonModel.getUserModel();
                                    utils.setObject(userModel.userName,userModel);

                                    finish();
                                }
                            }
                        });
        dialog = customBuilder.create();
        dialog.show();
    }
}
