package com.passwordkeep.zeromq.passwordkeep.activites;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.passwordkeep.zeromq.passwordkeep.R;
import com.passwordkeep.zeromq.passwordkeep.activites.model.CustomDialog;
import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;
import com.passwordkeep.zeromq.passwordkeep.activites.utils.SaveObjectUtils;
import com.passwordkeep.zeromq.passwordkeep.activites.model.SingletonModel;
import com.passwordkeep.zeromq.passwordkeep.activites.model.UserModel;
import com.wei.android.lib.fingerprintidentify.FingerprintIdentify;
import com.wei.android.lib.fingerprintidentify.base.BaseFingerprint;

import java.util.LinkedList;
import java.util.List;

public class LoginActivity extends BaseActivity {

    private EditText pass1,pass2,pass3,pass4;
    private SingletonModel singletonModel;
    SaveObjectUtils utils;
    private static final String key=LoginActivity.class.getSimpleName();
    private long exitTime = 0;
    private CustomDialog dialog;
    private FingerprintIdentify mFingerprintIdentify;
    private static final int MAX_AVAILABLE_TIMES = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        utils=new SaveObjectUtils(this,key);
        singletonModel=SingletonModel.getInstance();
        // utils.onDestroy();


        mFingerprintIdentify = new FingerprintIdentify(getApplicationContext(), new BaseFingerprint.FingerprintIdentifyExceptionListener() {
            @Override
            public void onCatchException(Throwable exception) {
              Log.w("fingerPrint","\nException：" + exception.getLocalizedMessage());
            }
        });

        if (!mFingerprintIdentify.isFingerprintEnable()) {


        }
        else
        {
            fingerPrint();
        }

        UserModel userModel=utils.getObject("zero",UserModel.class);
        if(userModel==null) {
            userModel=new UserModel();
            userModel.PasswordKeepList=new LinkedList<PasswordKeepModel>();
        }
        singletonModel.setUser(userModel);



        pass1 = (EditText) findViewById(R.id.editText1);
        pass2 = (EditText) findViewById(R.id.editText2);
        pass3 = (EditText) findViewById(R.id.editText3);
        pass4 = (EditText) findViewById(R.id.editText4);

        pass1.addTextChangedListener(textWatcher);
        pass2.addTextChangedListener(textWatcher);
        pass3.addTextChangedListener(textWatcher);
        pass4.addTextChangedListener(textWatcher);

        onWindowFocusChanged(true);

    }

    private TextWatcher textWatcher = new TextWatcher() {

        // @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        // @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            Log.w("Log", "----" + s);

        }

        // @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().length() == 1) {
                if (pass1.isFocused()) {
                    pass1.clearFocus();
                    pass2.requestFocus();
                } else if (pass2.isFocused()) {
                    pass2.clearFocus();
                    pass3.requestFocus();
                } else if (pass3.isFocused()) {
                    pass3.clearFocus();
                    pass4.requestFocus();
                } else if (pass4.isFocused()) {
                    pass4.clearFocus();

                    int inputPassword=Integer.parseInt(pass1.getText().toString()+pass2.getText().toString()+pass3.getText().toString()+pass4.getText().toString());

                    if(inputPassword==singletonModel.getUserModel().loginPassword)
                    {
                        loginSuccess();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        pass1.setText("");
                        pass2.setText("");
                        pass3.setText("");
                        pass4.setText("");
                    }

                }
            }
        }
    };

    public boolean dispatchKeyEvent( KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {//监听到删除按钮被按下
            if(pass1.isFocused())
            {
                pass1.setText("");
            }
            else if (pass2.isFocused()) {
               if(TextUtils.isEmpty(pass2.getText()))
               {
                   pass2.clearFocus();
                   pass1.requestFocus();
               }
               else
               {
                   pass2.setText("");
               }

            } else if (pass3.isFocused()) {
                if(TextUtils.isEmpty(pass3.getText()))
                {
                    pass3.clearFocus();
                    pass2.requestFocus();
                }
                else
                {
                    pass3.setText("");
                }

            } else if (pass4.isFocused()) {
                if(TextUtils.isEmpty(pass4.getText()))
                {
                    pass4.clearFocus();
                    pass3.requestFocus();
                }
                else
                {
                    pass4.setText("");
                }

            }
        }

        return super.dispatchKeyShortcutEvent(event);
    }

    private Handler hander=new Handler(){
        public void handleMessage(android.os.Message msg) {
            pass1.setFocusable(true);
            pass1.setFocusableInTouchMode(true);
            pass1.requestFocus();
            InputMethodManager inputManager = (InputMethodManager)pass1.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(pass1, 0);
        };
    };

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if(true){
            hander.sendEmptyMessageDelayed(0, 500);
        }
    }


    public void fingerPrint()
    {

        CustomDialog.Builder customBuilder = new
                CustomDialog.Builder(LoginActivity.this);
        customBuilder.setTitle("Finger print")
                .setMessage("Use you finger print ?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        mFingerprintIdentify.cancelIdentify();
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                mFingerprintIdentify.startIdentify(MAX_AVAILABLE_TIMES, new BaseFingerprint.FingerprintIdentifyListener() {
                                    @Override
                                    public void onSucceed() {
                                        Log.w("fingerPrint","\n" + ("successful"));

                                        loginSuccess();
                                    }

                                    @Override
                                    public void onNotMatch(int availableTimes) {
                                        Log.w("fingerPrint","\n" + ("no match"));

                                        Toast.makeText(getApplicationContext(), "no match",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onFailed(boolean isDeviceLocked) {
                                        Log.w("fingerPrint","\n" + "failed  " + isDeviceLocked);
                                        Toast.makeText(getApplicationContext(), "failed",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onStartFailedByDeviceLocked() {
                                        Log.w("fingerPrint","\n" + "start failed");
                                        Toast.makeText(getApplicationContext(), "start failed",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });
        dialog = customBuilder.create();
        dialog.show();
    }


    public void loginSuccess()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(pass4.getWindowToken(), 0);

        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);

    }

}
