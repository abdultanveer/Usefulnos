package com.abdul.uselessnos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CallLogAdapterRecyclerView  extends RecyclerView.Adapter<CallLogAdapterRecyclerView.CallLogViewHolder> {
    ArrayList<WorkingPhoneNo> workingPhoneNos;
    Context mContext;
    public CallLogAdapterRecyclerView(ArrayList<WorkingPhoneNo> workingPhoneNos,Context context) {
        this.workingPhoneNos = workingPhoneNos;
        mContext = context;

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

    }

    //box holding the planks -- return each plank for onBindViewHolder to write
    class CallLogViewHolder extends RecyclerView.ViewHolder{
        TextView phoneTextView;
        CheckBox workingCheckBox;
        Spinner categorySpinner;

        public CallLogViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneTextView = itemView.findViewById(R.id.textViewPhoneno);
            workingCheckBox = itemView.findViewById(R.id.checkBoxWorking);
            categorySpinner = itemView.findViewById(R.id.spinnerCategories);
        }
    }

}
