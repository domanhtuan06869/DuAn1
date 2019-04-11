package com.cao.nang.duan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cao.nang.duan.R;
import com.cao.nang.duan.drugandhopistal.Sick_activity;
import com.cao.nang.duan.model.SickList;

import java.util.List;

public class SickAdapter extends RecyclerView.Adapter<SickAdapter.MyViewHolder> {
    Context context;
    List<SickList> listdr;


    public SickAdapter(Context c, List<SickList> list) {
        context = c;
        listdr = list;
    }

    @NonNull
    @Override
    public SickAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return  new SickAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemlistlick,viewGroup,false));
    }



    @Override
    public void onBindViewHolder(@NonNull SickAdapter.MyViewHolder holder, int position) {
        final int i=position;
        final SickList sickList=listdr.get(position);
        holder.nameSick.setText(sickList.getSick_name());
        holder.setClickListener(new SickAdapter.ItemClickListener() {
            @Override
            public void onClickItem(int pos) {
                openDetailActivity(sickList.getSick_name(),sickList.getTreatment());
            }

            @Override
            public void onLongClickItem(int pos) {

            }
        });

    }
    private void openDetailActivity(String...details)
    {
  Intent i=new Intent(context, Sick_activity.class);

      i.putExtra("name",details[0]);

        i.putExtra("cachchua",details[1]);


       context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listdr.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private SickAdapter.ItemClickListener mListener;
        TextView nameSick;


        // Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
           nameSick=  itemView.findViewById(R.id.tvitemsick);
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

        public void setClickListener(SickAdapter.ItemClickListener listener) {
            this.mListener = listener;
        }



    }


    public interface ItemClickListener {
        void onClickItem(int pos);

        void onLongClickItem(int pos);
    }
}
