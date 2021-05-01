package com.abdul.uselessnos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WorkingNosAdapter extends RecyclerView.Adapter<WorkingNosAdapter.WorkingViewHolder> {
    Context mContext;
    ArrayList<WorkingPhoneNo> mWorkingPhoneNos;

    public WorkingNosAdapter(Context context, ArrayList<WorkingPhoneNo> arrayList) {
        mContext = context;
        mWorkingPhoneNos = arrayList;
    }

    @NonNull
    @Override
    public WorkingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_working_nos,parent,false);
        return new WorkingViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkingViewHolder holder, int position) {
        holder.workingphoneTextView.setText(mWorkingPhoneNos.get(position).getPhoneNo());
        holder.categoryTextView.setText(mWorkingPhoneNos.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return mWorkingPhoneNos.size();
    }

    public class WorkingViewHolder extends RecyclerView.ViewHolder {
        TextView workingphoneTextView, categoryTextView;
        public WorkingViewHolder(@NonNull View itemView) {
            super(itemView);
            workingphoneTextView = itemView.findViewById(R.id.textViewworkingPhoneno);
            categoryTextView = itemView.findViewById(R.id.textViewCategory);
        }
    }
}
