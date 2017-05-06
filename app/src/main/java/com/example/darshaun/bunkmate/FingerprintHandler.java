package com.example.darshaun.bunkmate;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

/**
 * Created by Darshaun on 30-Apr-17.
 */

public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {
    private Context context;


    // Constructor
    public FingerprintHandler(Context mContext) {
        context = mContext;
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }


    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString, false);
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString, false);
    }


    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed.", false);
    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        //this.update("Fingerprint Authentication succeeded.", true);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        if (!prefs.getBoolean("firstTime", false)) {
            Intent intent = new Intent(context, first.class);
            context.startActivity(intent);
            ((Activity) context).finish();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.putString("stopped","nowhere");
            editor.commit();
        }
        else {
            if(!prefs.getBoolean("finish", false)){
                Intent in;
                switch (prefs.getString("stopped", "")) {
                    case "nowhere":
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.dropall();
                        in = new Intent(context, first.class);
                        context.startActivity(in);
                        ((Activity) context).finish();
                        break;
                    case "first":
                        in = new Intent(context, Minimum.class);
                        context.startActivity(in);
                        ((Activity) context).finish();
                        break;
                    case "minimum":
                        in = new Intent(context, Monday.class);
                        context.startActivity(in);
                        ((Activity) context).finish();
                        break;
                    case "monday":
                        in = new Intent(context, Tuesday.class);
                        context.startActivity(in);
                        ((Activity) context).finish();
                        break;
                    case "tuesday":
                        in = new Intent(context, Wednesday.class);
                        context.startActivity(in);
                        ((Activity) context).finish();
                        break;
                    case "wednesday":
                        in = new Intent(context, Thursday.class);
                        context.startActivity(in);
                        ((Activity) context).finish();
                        break;
                    case "thursday":
                        in = new Intent(context, Friday.class);
                        context.startActivity(in);
                        ((Activity) context).finish();
                        break;
                    case "friday":
                        in = new Intent(context, Saturday.class);
                        context.startActivity(in);
                        ((Activity) context).finish();
                        break;
                    case "saturday":
                    default:
                        in = new Intent(context, Daydisplay.class);
                        context.startActivity(in);
                        ((Activity) context).finish();
                        break;
                }
            }
            else{
                Intent in = new Intent(context, Daydisplay.class);
                context.startActivity(in);
                ((Activity) context).finish();
            }
        }
    }


    public void update(String e, Boolean success){
        TextView textView = (TextView) ((Activity)context).findViewById(R.id.errorText);
        textView.setText(e);
        if(success){
            textView.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
        }
    }
}
