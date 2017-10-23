package com.passwordkeep.zeromq.passwordkeep.activites.model;

import java.io.Serializable;

/**
 * Created by VMLDEV6 on 14/09/2017.
 */

public class PasswordTypeModel implements Serializable {
    private String title;
    private int icon;
    private int count;


    public PasswordTypeModel(String title , int icon, int count)
    {
        this.title=title;
        this.icon=icon;
        this.count=count;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
