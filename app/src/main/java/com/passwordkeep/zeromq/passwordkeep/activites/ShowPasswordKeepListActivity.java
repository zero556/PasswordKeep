package com.passwordkeep.zeromq.passwordkeep.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.adapter.PasswordShowListAdapter;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.SingletonModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.UserModel;

import java.util.LinkedList;
import java.util.List;

public class ShowPasswordKeepListActivity extends TitleActivity {

    private List<PasswordKeepModel> pData=null;
    private Context pContext;
    private PasswordShowListAdapter passwordShowAdapter=null;
    private ListView plistView;
    private SingletonModel singletonModel;
    private String type =null;
    private int iconIndex=-1;
    private boolean isTrash=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_password_keep);
        singletonModel= SingletonModel.getInstance();
        setTitle("");
        showBackwardView(R.string.fa_chevron_circle_left,true);
        showForwardView(R.string.fa_trash,true);

        Button mForwardButton = (Button) findViewById(R.id.button_forward);
        mForwardButton.setTextSize(25);
        mForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int itemCount = plistView.getLastVisiblePosition() - plistView.getFirstVisiblePosition() + 1;
               // passwordShowAdapter.areAllItemsEnabled();
                if(!isTrash) {

                    for (int i = 0; i < itemCount; i++)
                        ((SwipeLayout) (plistView.getChildAt(i))).open(true);
                    isTrash=true;
                }
                else
                {

                    for (int i = 0; i < itemCount; i++)
                        ((SwipeLayout) (plistView.getChildAt(i))).close(true);
                    isTrash=false;
                }

            }
        });

        Button mBackwardButton = (Button) findViewById(R.id.button_backward);
        mBackwardButton.setTextSize(28);
        mBackwardButton.setBackground(null);

        Bundle bundle = this.getIntent().getExtras();
        if(bundle!=null)
        {
            type = bundle.getString("title");
            iconIndex=bundle.getInt("iconIndex");
        }
        bulidListView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        bulidListView();
    }

    public void bulidListView(){
        if(type!=null) {

            UserModel userModel=singletonModel.getUserModel();
            pData = new LinkedList<PasswordKeepModel>();
            for(PasswordKeepModel p : userModel.PasswordKeepList) {
                if(p.getPasswordType().getTitle().equals(type))
                {
                    pData.add(p);
                }
            }

            if(pData.size()>0) {


                pContext = ShowPasswordKeepListActivity.this;
                plistView = (ListView) findViewById(R.id.listViewShowPassword);

                passwordShowAdapter = new PasswordShowListAdapter((LinkedList<PasswordKeepModel>) pData, pContext);
                plistView.setAdapter(passwordShowAdapter);

                plistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override

                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        TextView textId = (TextView) view.findViewById(R.id.textViewId);
                        String id = textId.getText().toString();

                        UserModel userModel = singletonModel.getUserModel();
                        PasswordKeepModel passwordKeepModel = new PasswordKeepModel();
                        for (PasswordKeepModel p : userModel.PasswordKeepList) {
                            if (p.getId().equals(id)) {
                                passwordKeepModel = p;
                            }
                        }

                        Intent intent = new Intent(ShowPasswordKeepListActivity.this, ShowPasswordKeepDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("passwordKeepModel", passwordKeepModel);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
            else
            {
                finish();
            }
        }
    }
}
