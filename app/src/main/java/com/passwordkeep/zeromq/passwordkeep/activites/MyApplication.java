package com.passwordkeep.zeromq.passwordkeep.activites;

/**
 * Created by VMLDEV6 on 21/09/2017.
 */

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by zeroyao on 21/9/17.
 */
public class MyApplication extends Application {
    public int count = 0;
    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityStopped(Activity activity) {
                Log.v("viclee", activity + "onActivityStopped");
                count--;
                if (count == 0) {
                    Log.v("viclee", ">>>>>>>>>>>>>>>>>>>切到后台  lifecycle");
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.v("viclee", activity + "onActivityStarted");
                if (count == 0) {

                    String callbackActivity = activity.getClass().getName();

                    Intent i = new Intent(activity, LoginActivity.class);
                    i.putExtra("target", callbackActivity);

                    Bundle params = activity.getIntent().getExtras();
                    if (params != null) {
                        i.putExtras(params);
                    }
                    activity.startActivity(i);
                    //dialog.dismiss();
                    Log.v("viclee", ">>>>>>>>>>>>>>>>>>>切到前台  lifecycle");
                }
                count++;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.v("viclee", activity + "onActivitySaveInstanceState");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.v("viclee", activity + "onActivityResumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.v("viclee", activity + "onActivityPaused");
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.v("viclee", activity + "onActivityDestroyed");
            }

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.v("viclee", activity + "onActivityCreated");
            }
        });
    }
}
