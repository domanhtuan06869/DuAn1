package com.cao.nang.duan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cao.nang.duan.MapsActivity;
import com.cao.nang.duan.R;
import com.cao.nang.duan.model.CategoryDrug;
import com.cao.nang.duan.ListCategoryDrug;
import com.cao.nang.duan.model.Hospital;

import java.util.List;

public class FindHospitalAdapter extends RecyclerView.Adapter<FindHospitalAdapter.MyViewHolder> {
    Context context;
    List<Hospital> listdr;


    public FindHospitalAdapter(Context c, List<Hospital> list) {
        context = c;
        listdr = list;
    }

    @NonNull
    @Override
    public FindHospitalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FindHospitalAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_list_find_hospital,viewGroup,false));
    }



    @Override
    public void onBindViewHolder(@NonNull FindHospitalAdapter.MyViewHolder holder, int position) {
        final int i=position;
        final Hospital hospital=listdr.get(position);
        holder.name_hospital.setText(hospital.getName_hospital());

        holder.setClickListener(new FindHospitalAdapter.ItemClickListener() {
            @Override
            public void onClickItem(int pos) {
                openDetailActivity();
            }

            @Override
            public void onLongClickItem(int pos) {

            }
        });

    }
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(context, MapsActivity.class);

    //    i.putExtra("List_drug",details[0]);
        // i.putExtra("DESC_KEY",details[1]);
        //  i.putExtra("PROP_KEY",details[2]);

        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listdr.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private FindHospitalAdapter.ItemClickListener mListener;
        TextView name_hospital;


        // Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
           name_hospital=  itemView.findViewById(R.id.tvNameHospital);
            itemView.setOnClickListener((View.OnClickListener) this);


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mListener.onClickItem(getLayoutPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            mListener.onClickItem(getLayoutPosition());
            return true;
        }

        public void setClickListener(FindHospitalAdapter.ItemClickListener listener) {
            this.mListener = listener;
        }



    }


    public interface ItemClickListener {
        void onClickItem(int pos);

        void onLongClickItem(int pos);
    }
}
