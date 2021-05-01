package com.abdul.uselessnos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        workingPhoneNos.add(new WorkingPhoneNo("1234567",true,"oxygen"));
        workingPhoneNos.add(new WorkingPhoneNo("1234567",false,"bed"));
        workingPhoneNos.add(new WorkingPhoneNo("1234567",true,"remdesvir"));
        workingPhoneNos.add(new WorkingPhoneNo("1234567",false,"vaccine"));
        workingPhoneNos.add(new WorkingPhoneNo("1234567",true,"oxygen"));

    }

}