package com.example.coronavirustracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {
    private Activity mActivity;
    private AlertDialog mAlertDialog;

    public LoadingDialog(Activity mActivity){
        this.mActivity=mActivity;
    }

    public void startLoadingDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(mActivity);

        LayoutInflater layoutInflater=mActivity.getLayoutInflater();

        builder.setView(layoutInflater.inflate(R.layout.loading_alert_dialog,null));
        builder.setCancelable(false);

        mAlertDialog=builder.create();
        mAlertDialog.show();
    }

    public void dismissLoadingDialog(){
        if(mAlertDialog!=null){
            mAlertDialog.dismiss();
        }
    }
}

