package com.passwordkeep.zeromq.passwordkeep.activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordRegisterDialog;
import com.passwordkeep.zeromq.passwordkeep.activites.model.SingletonModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.UserModel;
import com.passwordkeep.zeromq.passwordkeep.activites.utils.Installation;
import com.passwordkeep.zeromq.passwordkeep.activites.utils.SaveObjectUtils;

import java.util.List;

public class RegisterActivity extends BaseActivity {

    private PasswordRegisterDialog dialog;
    PasswordRegisterDialog.Builder customBuilder;
    private SingletonModel singletonModel;
    SaveObjectUtils utils;
    private static final String key=LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        ImageView imageViewPasswordInput=(ImageView) this.findViewById(R.id.imageViewpasswordInput);
        imageViewPasswordInput.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                popOutPasswordRegisterDialog();


            }
        });
    }
    public void popOutPasswordRegisterDialog()
    {

        customBuilder = new
                PasswordRegisterDialog.Builder(RegisterActivity.this);
        customBuilder.setTitle("Use password register")
                .setMessage("Delete keep ?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                createAccount();
                            }
                        });
        dialog = customBuilder.create();
        dialog.show();

    }

    public void createAccount()
    {
        String InstallationId = Installation.id(RegisterActivity.this);
        String userName=  customBuilder.getInputUserName();
        String password=  customBuilder.getInputPassword();

        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password))
        {
            Toast.makeText(getApplicationContext(), "Input all information please!",
                    Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(RegisterActivity.this,MainActivity.class);


            return;

        }


        UserModel userModel=new UserModel(userName,Integer.parseInt(password),InstallationId);
        utils=new SaveObjectUtils(this,key);
        singletonModel= SingletonModel.getInstance();
        utils.setObject(InstallationId,userModel);
        singletonModel.setUser(userModel);

        Toast.makeText(getApplicationContext(), "Register successful!",
                Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
    }
}


