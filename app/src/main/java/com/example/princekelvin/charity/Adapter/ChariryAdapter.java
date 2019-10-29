package com.example.princekelvin.charity.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.princekelvin.charity.AddEditCharityActivity;
import com.example.princekelvin.charity.Database.CharityDatabase;
import com.example.princekelvin.charity.Model.CharityModel;
import com.example.princekelvin.charity.R;
import com.example.princekelvin.charity.ViewCharityActivity;
import com.example.princekelvin.charity.databinding.CharityItemBinding;

import java.util.ArrayList;

public class ChariryAdapter extends RecyclerView.Adapter<ChariryAdapter.CharityViewHolder> {

    private ArrayList<CharityModel> charityModelArrayList;
    private Context context;

    public ChariryAdapter(ArrayList<CharityModel> charityModelArrayList, Context ctx) {
        this.charityModelArrayList = charityModelArrayList;
        this.context = ctx;
    }

    @NonNull
    @Override
    public CharityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CharityItemBinding charityItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.charity_item, parent, false);
        return new CharityViewHolder(charityItemBinding.getRoot(), context);
    }

    @Override
    public void onBindViewHolder(@NonNull CharityViewHolder holder, int position) {

        final CharityModel charityModel = charityModelArrayList.get(position);
        holder.charityItemBinding.setCharity(charityModel);
        holder.charityItemBinding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editDetails = new Intent(context, AddEditCharityActivity.class);
                editDetails.putExtra("TITLE", charityModel.getTitle());

                Log.d("MIMI", "onClick: "+ charityModel.getId());

                editDetails.putExtra("sDATE", charityModel.getStart_date());
                editDetails.putExtra("eDate", charityModel.getEnd_date());
                editDetails.putExtra("Description", charityModel.getDescription());
                editDetails.putExtra("TargetAmount", charityModel.getTarget_amount());
                editDetails.putExtra("Myid", charityModel.getId());
                editDetails.putExtra("formatType", "EDIT");
                context.startActivity(editDetails);
            }
        });
        holder.charityItemBinding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Charity Event")
                        .setMessage("Are you sure you want to delete this Charity Event?")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                DeleteAsyncTask asyncTask = new DeleteAsyncTask();
                                asyncTask.execute(charityModel);
                                Toast.makeText(context, "Charity event deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        });
    }

    private class DeleteAsyncTask extends AsyncTask<CharityModel, Void, Void> {
        private CharityDatabase charityDatabase = CharityDatabase.getInstance(context);

        @Override
        protected Void doInBackground(CharityModel... charityModels) {
            charityDatabase.charityDao().deleteCharity(charityModels[0]);
            return null;
        }

    }

    @Override
    public int getItemCount() {
        return charityModelArrayList.size();
    }

    public static class CharityViewHolder extends RecyclerView.ViewHolder {

        CharityItemBinding charityItemBinding;

        public CharityViewHolder(View itemView, final Context context) {
            super(itemView);
            charityItemBinding = DataBindingUtil.bind(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharityModel charityModel = charityItemBinding.getCharity();

                    Intent viewDetails = new Intent(context, ViewCharityActivity.class);
                    viewDetails.putExtra("TITLE", charityModel.getTitle());
                    viewDetails.putExtra("sDATE", charityModel.getStart_date());
                    viewDetails.putExtra("eDate", charityModel.getEnd_date());
                    viewDetails.putExtra("Description", charityModel.getDescription());
                    viewDetails.putExtra("TargetAmount", charityModel.getTarget_amount());
                    context.startActivity(viewDetails);
                }
            });
        }
    }

}