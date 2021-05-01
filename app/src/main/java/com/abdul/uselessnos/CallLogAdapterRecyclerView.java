package com.abdul.uselessnos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CallLogAdapterRecyclerView  extends RecyclerView.Adapter<CallLogAdapterRecyclerView.CallLogViewHolder> {
    ArrayList<WorkingPhoneNo> workingPhoneNos;
    FirebaseFirestore db;
    Context mContext;
    String TAG = CallLogAdapterRecyclerView.class.getSimpleName();
    public CallLogAdapterRecyclerView(ArrayList<WorkingPhoneNo> workingPhoneNos,Context context) {
        this.workingPhoneNos = workingPhoneNos;
        mContext = context;
         db = FirebaseFirestore.getInstance();

    }

    @NonNull
    @Override ///create a wooden plank for the menu
    public CallLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_call_log,parent,false);
        return new CallLogViewHolder(view);
    }

    @Override //keep count of no items
    public int getItemCount() {
        return workingPhoneNos.size();
    }

    @Override //write on the planks
    public void onBindViewHolder(@NonNull CallLogViewHolder holder, int position) {
        holder.phoneTextView.setText(workingPhoneNos.get(position).getPhoneNo());
        //holder.submitButton.setOnCheckedChangeListener(null);
        holder.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phno = workingPhoneNos.get(position).getPhoneNo();
                String category = holder.categorySpinner.getSelectedItem().toString();
                // String category = workingPhoneNos.get(position).getCategory();
                //Toast.makeText(mContext, category + "---"+phno , Toast.LENGTH_SHORT).show();
                Log.i(TAG,"ph-cate"+phno+"---"+category);
                addData(phno,category);
                holder.submitButton.setText("submitted");
                holder.submitButton.setEnabled(false);
            }
        });



    }

    //box holding the planks -- return each plank for onBindViewHolder to write
    class CallLogViewHolder extends RecyclerView.ViewHolder{
        TextView phoneTextView;
        Button submitButton;
        Spinner categorySpinner;

        public CallLogViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneTextView = itemView.findViewById(R.id.textViewPhoneno);
            submitButton = itemView.findViewById(R.id.checkBoxWorking);
            categorySpinner = itemView.findViewById(R.id.spinnerCategories);
        }
    }

    private void addData(String phone,String category){

        Map<String, Object> user = new HashMap<>();
        user.put("phone", phone);
        user.put("category", category);
        user.put("timestamp", Timestamp.now());

// Add a new document with a generated ID
        db.collection("phonenos")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void readData(){
        db.collection("phonenos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}
