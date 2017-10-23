package com.passwordkeep.zeromq.passwordkeep.activites;

import android.app.Activity;
import android.os.Bundle;

import com.passwordkeep.zeromq.passwordkeep.activites.model.AppManager;

/**
 * Created by VMLDEV6 on 10/10/2017.
 */

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
    }

    protected void finishAllActivity()
    {
        super.finish();
        AppManager.getInstance().finishAllActivity();
    }
}
