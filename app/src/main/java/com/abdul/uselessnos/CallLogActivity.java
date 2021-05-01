package com.abdul.uselessnos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CallLogActivity extends AppCompatActivity  {
    private static final String TAG = CallLogActivity.class.getSimpleName() ;
    ArrayList<WorkingPhoneNo> workingPhoneNos;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);
        getCallLog();
        db = FirebaseFirestore.getInstance();

        RecyclerView callLogListView = findViewById(R.id.callLogListView);
        CallLogAdapterRecyclerView adapter = new CallLogAdapterRecyclerView(workingPhoneNos,this,db);
        callLogListView.setLayoutManager(new LinearLayoutManager(this));
        callLogListView.setAdapter(adapter);

        RecyclerView recentCallsRV = findViewById(R.id.recentCallsListView);
        ArrayList<WorkingPhoneNo> arrayList = readDataFirebase();
        WorkingNosAdapter workingNosAdapter = new WorkingNosAdapter(this,arrayList);
        callLogListView.setLayoutManager(new LinearLayoutManager(this));
        callLogListView.setAdapter(workingNosAdapter);

       // callLogListView.setOnItemClickListener(this);
       /* ArrayAdapter<WorkingPhoneNo> adapter = new ArrayAdapter<WorkingPhoneNo>(this,
                R.layout.row_call_log,R.id.textViewPhoneno,workingPhoneNos);
        callLogListView.setAdapter(adapter);*/
    }

    private void getCallLog(){
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

    private ArrayList<WorkingPhoneNo> readDataFirebase(){
        ArrayList<WorkingPhoneNo> list = new ArrayList<WorkingPhoneNo>();

        db.collection("phonenos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String phoneno = document.getString("phone");
                                String category = document.getString("category");
                                WorkingPhoneNo workingPhoneNo = new WorkingPhoneNo(phoneno,true,category);
                                list.add(workingPhoneNo);
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return list;
    }
}

