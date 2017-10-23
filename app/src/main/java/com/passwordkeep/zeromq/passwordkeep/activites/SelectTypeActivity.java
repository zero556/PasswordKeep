package com.passwordkeep.zeromq.passwordkeep.activites;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.adapter.PasswordTypeAdapter;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordTypeModel;

import java.util.LinkedList;
import java.util.List;

public class SelectTypeActivity extends TitleActivity {

    private List<PasswordTypeModel> pData=null;
    private Context pContext;
    private PasswordTypeAdapter passwordTypeAdapter=null;
    private ListView plistView;
    private int selectedIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);

        setTitle("");
        selectedIndex=-1;
       //
        showBackwardView(R.string.fa_chevron_circle_left,true);
        showForwardView(R.string.a_check,true);
        Button mForwardButton = (Button) findViewById(R.id.button_forward);
        mForwardButton.setTextSize(20);

        Button mBackwardButton = (Button) findViewById(R.id.button_backward);
        mBackwardButton.setTextSize(28);
        mBackwardButton.setBackground(null);


        mForwardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent=new Intent(SelectTypeActivity.this,CreateActivity.class);
                String title=null;
                int iconIndex=-1;
                int i=0;
                if(selectedIndex!=-1) {

                    for (PasswordTypeModel p : pData) {
                        if(i==selectedIndex)
                        {
                            title=p.getTitle();
                            iconIndex=p.getIcon();
                        }
                        i++;
                    }
                    Bundle bundle=new Bundle();
                    bundle.putString("title",title);
                    bundle.putInt("iconIndex",iconIndex);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(pContext, "please select a type", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Typeface font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        pContext=SelectTypeActivity.this;
        plistView=(ListView)findViewById(R.id.listView_type);
        pData=new LinkedList<PasswordTypeModel>();
        pData.add(new PasswordTypeModel("WebSite",R.string.fa_chrome,R.string.fa_check_square));
        pData.add(new PasswordTypeModel("Server",R.string.fa_server,R.string.fa_check_square));
        pData.add(new PasswordTypeModel("Game",R.string.fa_gamepad,R.string.fa_check_square));
        pData.add(new PasswordTypeModel("Bank",R.string.fa_bank,R.string.fa_check_square));
        pData.add(new PasswordTypeModel("Chat",R.string.fa_wechat,R.string.fa_check_square));
        pData.add(new PasswordTypeModel("Windows",R.string.fa_windows,R.string.fa_check_square));
        pData.add(new PasswordTypeModel("Weibo",R.string.fa_weibo,R.string.fa_check_square));
        pData.add(new PasswordTypeModel("Other",R.string.fa_shopping_basket,R.string.fa_check_square));
        passwordTypeAdapter=new PasswordTypeAdapter((LinkedList<PasswordTypeModel>) pData,pContext);
//
        plistView.setAdapter(passwordTypeAdapter);

        plistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view.findViewById(R.id.textViewCount);
                if(selectedIndex!=i || selectedIndex==-1) {
                    View mView = plistView.getChildAt(selectedIndex);
                    if(mView!=null) {
                        TextView textViewSelected = (TextView) mView.findViewById(R.id.textViewCount);
                        textViewSelected.setTextColor(getResources().getColor(R.color.colorIcon));

                    }
                    selectedIndex=i;

                    textView.setTextColor(getResources().getColor(R.color.btn_register_normal));
                }
                else
                {
                    selectedIndex=-1;
                    textView.setTextColor(getResources().getColor(R.color.colorIcon));
                }

            }
        });


    }
}
