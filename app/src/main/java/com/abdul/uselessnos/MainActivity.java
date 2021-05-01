package com.abdul.uselessnos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().getExtras()!= null) {
            String phno = getIntent().getExtras().getString("phone_number");
            showDialog(this,phno);
        }
    }

    private void showDialog(Context context, String phno) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("was this"+phno+" usefule");
        builder.setPositiveButton("useful", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //inform firebase that this no was useful
                Log.i(TAG, "no is useful");
            }
        });

        builder.setNegativeButton("NOT USEFUL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //inform firebase that this no was useful

                Log.i(TAG, "no was not useful");

            }
        });
        builder.create().show();
       // finish();
    }


    public boolean takePermission() {
        int REQUEST_CODE = 123;
            int readPhoneState = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
            int read_call_log = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);

            List listPermissionsNeeded = new ArrayList<>();

            if (readPhoneState != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_PHONE_STATE);
            }

            if (read_call_log != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.READ_CALL_LOG);
            }

            if (read_call_log != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.PROCESS_OUTGOING_CALLS);
            }

            if (read_call_log != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(Manifest.permission.INTERNET);
            }

            if (!listPermissionsNeeded.isEmpty()) {
                ActivityCompat.requestPermissions(this,
                        (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                        REQUEST_CODE);

                return false;
            }
            return true;
        }
    }
