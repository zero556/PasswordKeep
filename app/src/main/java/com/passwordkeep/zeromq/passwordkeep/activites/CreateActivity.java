package com.passwordkeep.zeromq.passwordkeep.activites;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.adapter.PasswordCreateAdapter;
import com.passwordkeep.zeromq.passwordkeep.activites.model.CustomDialog;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordTypeModel;
import com.passwordkeep.zeromq.passwordkeep.activites.utils.Installation;
import com.passwordkeep.zeromq.passwordkeep.activites.utils.SaveObjectUtils;
import com.passwordkeep.zeromq.passwordkeep.activites.model.SingletonModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.UserModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class CreateActivity extends TitleActivity {


    private ArrayList<String[]> itemlist=null;
    private Context pContext;
    private PasswordCreateAdapter passwordCreateAdapter=null;
    private ListView plistView;
    private SingletonModel singletonModel;
    private String type =null;
    private int iconIndex=-1;
    SaveObjectUtils utils;
    private static final String key=LoginActivity.class.getSimpleName();
    private CustomDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        singletonModel= SingletonModel.getInstance();
        utils=new SaveObjectUtils(this,key);
        Bundle bundle = this.getIntent().getExtras();


        if(bundle!=null)
        {
            type = bundle.getString("title");
            iconIndex=bundle.getInt("iconIndex");
        }

        if(type==null)
        {
            type="WebSite";
            iconIndex=R.string.fa_chrome;
        }


        setTitle("");
        showBackwardView(R.string.fa_chevron_circle_left,true);
        Button mBackwardButton = (Button) findViewById(R.id.button_backward);
        mBackwardButton.setTextSize(28);
        mBackwardButton.setBackground(null);

        showForwardView(R.string.fa_plus_square,true);
        Button mForwardButton = (Button) findViewById(R.id.button_forward);
        mForwardButton.setTextSize(25);


        mForwardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                savePasswordKeep();
            }
        });


        itemlist = new ArrayList<String[]>() ;
        String[] item={"Title","Select Type :"};
        itemlist.add(new String[]{"title","Select Type :"});
        itemlist.add(new String[]{"type",type,iconIndex+""});
        itemlist.add(new String[]{"title","Fill in Content :"});
        itemlist.add(new String[]{"content",""});
        itemlist.add(new String[]{"title","Fill in Remark :"});
        itemlist.add(new String[]{"remark",""});
        itemlist.add(new String[]{"title","Add Picture:"});
        itemlist.add(new String[]{"picture",""});

        pContext=CreateActivity.this;
        plistView=(ListView)findViewById(R.id.listView_list);

        passwordCreateAdapter=new PasswordCreateAdapter(itemlist,pContext);
//
        plistView.setAdapter(passwordCreateAdapter);


        plistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==1)
                {
                    Intent intent=new Intent(CreateActivity.this,SelectTypeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void saveOnClick(View view) {



    }



    public void savePasswordKeep()
    {
        CustomDialog.Builder customBuilder = new
                CustomDialog.Builder(this);
        customBuilder.setTitle("Create")
                .setMessage("Create keep ?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                PasswordTypeModel passwordType =new PasswordTypeModel(type, iconIndex,0 );


                                TextView textTitle=(TextView)findViewById(R.id.createTitle);
                                TextView textAccounts=(TextView)findViewById(R.id.createAccount);
                                TextView textPassword=(TextView)findViewById(R.id.createPassword);
                                TextView textRemark=(TextView)findViewById(R.id.createRemark);
                                PasswordKeepModel passwordKeepModel =new PasswordKeepModel(
                                        textTitle.getText().toString(),
                                        textAccounts.getText().toString(),
                                        textPassword.getText().toString(),
                                        textRemark.getText().toString(),
                                        passwordType);

                                if(  singletonModel.getUserModel().PasswordKeepList==null)
                                {

                                    singletonModel.getUserModel().PasswordKeepList=new LinkedList<PasswordKeepModel>();

                                }

                                singletonModel.getUserModel().PasswordKeepList.add(passwordKeepModel);
                                String InstallationId = Installation.id(CreateActivity.this);
                                UserModel userModel=singletonModel.getUserModel();
                                utils.setObject(InstallationId,userModel);
                                Intent intent=new Intent(CreateActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        });
        dialog = customBuilder.create();
        dialog.show();






    }
    public void selectTypeOnClick(View view) {

    }
}
