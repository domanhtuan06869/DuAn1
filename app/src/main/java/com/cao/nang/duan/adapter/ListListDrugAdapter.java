package com.cao.nang.duan.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cao.nang.duan.drugandhopistal.ListCategoryDrug_activity;
import com.cao.nang.duan.R;
import com.cao.nang.duan.model.ListDrug;

import java.util.List;

public class ListListDrugAdapter extends RecyclerView.Adapter<ListListDrugAdapter.MyViewHolder> {
    Context context;
    List<ListDrug> listdr;


    public ListListDrugAdapter(Context c, List<ListDrug> list) {
        context = c;
        listdr = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_list_list_drug,viewGroup,false));
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final int i=position;
       final ListDrug listDrug =listdr.get(position);
       holder.name.setText(listDrug.getListdrug());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClickItem(int pos) {
           openDetailActivity(listDrug.getListdrug());
            }

            @Override
            public void onLongClickItem(int pos) {

            }
        });

    }
    private void openDetailActivity(String...details)
    {
       Intent i=new Intent(context, ListCategoryDrug_activity.class);

        i.putExtra("List_Category",details[0]);

  context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listdr.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ItemClickListener mListener;
        TextView name;


        // Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=  itemView.findViewById(R.id.tvDanhmuc);
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

        public void setClickListener(ItemClickListener listener) {
            this.mListener = listener;
        }



    }


    public interface ItemClickListener {
        void onClickItem(int pos);

        void onLongClickItem(int pos);
    }

}
