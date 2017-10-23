package com.passwordkeep.zeromq.passwordkeep.activites;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.adapter.PasswordTypeAdapter;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordTypeModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.SingletonModel;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends TitleActivity {

//    private  String[] titles= new String[]{"WebSite","Email","Chat","Other" };
//    private  String[] counts= new String[]{"0","0","0","0"};
//    private  int[] imgIds= new int[]{R.mipmap.website,R.mipmap.email,R.mipmap.chat,R.mipmap.contact};

    private List<PasswordTypeModel> pData=null;
    private Context pContext;
    private PasswordTypeAdapter passwordTypeAdapter=null;
    private ListView plistView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private AnimationDrawable mAnimationDrawable;
    private Toolbar toolbar;
    private SingletonModel singletonModel;
    private ImageView ivRunningMan;

    private int WebsiteCount=0;
    private int ServerCount=0;
    private int GameCount=0;
    private int BankCount=0;
    private int ChatCount=0;
    private int WindowsCount=0;
    private int WeiboCount=0;
    private int OtherCount=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bulidListView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        bulidListView();

    }

    public void addOnClick(View view) {
        Intent intent=new Intent(MainActivity.this,CreateActivity.class);
        startActivity(intent);

    }

    public  void bulidListView()
    {
        singletonModel= SingletonModel.getInstance();
        if(singletonModel.getUserModel().PasswordKeepList!=null)
        {
            countType();
        }

        setTitle("Password Keeper");
        showBackwardView(R.string.fa_th_large,true);
        Button mBackwardButton = (Button) findViewById(R.id.button_backward);
        mBackwardButton.setTextSize(23);
        mBackwardButton.setBackground(null);
        showForwardView(R.string.fa_search,true);



        mBackwardButton.setOnClickListener(new View.OnClickListener() {

          public void onClick(View arg0) {
             // InitialDrawerLayout();


          }
      });


        //InitialDrawerLayout();

        pContext=MainActivity.this;
        plistView=(ListView)findViewById(R.id.listView1);
        pData=new LinkedList<PasswordTypeModel>();
        pData.add(new PasswordTypeModel("WebSite",R.string.fa_chrome,WebsiteCount));
        pData.add(new PasswordTypeModel("Server",R.string.fa_server,ServerCount));
        pData.add(new PasswordTypeModel("Game",R.string.fa_gamepad,GameCount));
        pData.add(new PasswordTypeModel("Bank",R.string.fa_bank,BankCount));
        pData.add(new PasswordTypeModel("Chat",R.string.fa_wechat,ChatCount));
        pData.add(new PasswordTypeModel("Windows",R.string.fa_windows,WindowsCount));
        pData.add(new PasswordTypeModel("Weibo",R.string.fa_weibo,WeiboCount));
        pData.add(new PasswordTypeModel("Other",R.string.fa_shopping_basket,OtherCount));
        passwordTypeAdapter=new PasswordTypeAdapter((LinkedList<PasswordTypeModel>) pData,pContext);
        plistView.setAdapter(passwordTypeAdapter);



        plistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView textView = (TextView) view.findViewById(R.id.textViewTitle);
                String selectTitle= textView.getText().toString();

                TextView textViewCount = (TextView) view.findViewById(R.id.textViewCount);
                int count= Integer.parseInt( textViewCount.getText().toString());

                if(count>0 ) {
                    Intent intent=new Intent(MainActivity.this,ShowPasswordKeepListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title", selectTitle);
                    bundle.putInt("iconIndex", i);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }

    public void countType() {

        WebsiteCount=0;
        ServerCount=0;
        GameCount=0;
        BankCount=0;
        ChatCount=0;
        WindowsCount=0;
        WeiboCount=0;
        OtherCount=0;

        for (PasswordKeepModel P : singletonModel.getUserModel().PasswordKeepList) {
            if (P.getPasswordType().getTitle().equals("WebSite") ) {
                WebsiteCount++;
            }
            if (P.getPasswordType().getTitle().equals("Server")) {
                ServerCount++;
            }
            if (P.getPasswordType().getTitle().equals("Game") ) {
                GameCount++;
            }
            if (P.getPasswordType().getTitle().equals("Bank") ) {
                BankCount++;
            }
            if (P.getPasswordType().getTitle().equals("Weibo") ) {
                WeiboCount++;
            }
            if (P.getPasswordType().getTitle().equals("Chat") ) {
                ChatCount++;
            }
            if (P.getPasswordType().getTitle().equals("Windows") ) {
                WindowsCount++;
            }
            if (P.getPasswordType().getTitle().equals("Other") ) {
                OtherCount++;
            }

        }

    }

    public void InitialDrawerLayout()
    {
//        mAnimationDrawable = (AnimationDrawable) ivRunningMan.getBackground();
//        mAnimationDrawable.start();
//
//        //创建返回键，并实现打开关/闭监听
//        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.a_check, R.string.app_name) {
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                mAnimationDrawable.stop();
//            }
//            @Override
//            public void onDrawerClosed(View drawerView) {
//                super.onDrawerClosed(drawerView);
//                mAnimationDrawable.start();
//            }
//        };
//        mDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


}
