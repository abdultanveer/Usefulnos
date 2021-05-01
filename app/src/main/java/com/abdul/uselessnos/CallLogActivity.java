package com.abdul.uselessnos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CallLogActivity extends AppCompatActivity {
    List<WorkingPhoneNo> workingPhoneNos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        createList();
        ListView callLogListView = findViewById(R.id.callLogListView);
        ArrayAdapter<WorkingPhoneNo> adapter = new ArrayAdapter<WorkingPhoneNo>(this,
                R.layout.row_call_log,R.id.textViewPhoneno,workingPhoneNos);
        callLogListView.setAdapter(adapter);
    }

    private void createList(){
        workingPhoneNos = new ArrayList<>();

        Cursor managedCursor = getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null,null,null);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
      /*  while (managedCursor.moveToPrevious()){

            managedCursor.moveToPrevious();
        }*/
        int noOfRows = managedCursor.getCount();
        managedCursor.moveToLast();

        for(int i=managedCursor.getCount(); i>0; i--){
            managedCursor.moveToPosition(i-1);
            String phno = managedCursor.getString(number);
            workingPhoneNos.add(new WorkingPhoneNo(phno,false,"oxygen"));
        }


    }

}