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
import com.cao.nang.duan.model.CategoryDrug;
import com.cao.nang.duan.ListCategoryDrug;

import java.util.List;

public class SickAdapter extends RecyclerView.Adapter<SickAdapter.MyViewHolder> {
    Context context;
    List<CategoryDrug> listdr;


    public SickAdapter(Context c, List<CategoryDrug> list) {
        context = c;
        listdr = list;
    }

    @NonNull
    @Override
    public SickAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null ;// new SickAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_list_list_drug,viewGroup,false));
    }



    @Override
    public void onBindViewHolder(@NonNull SickAdapter.MyViewHolder holder, int position) {
        final int i=position;
        final CategoryDrug categoryDrug=listdr.get(position);
        holder.name.setText(categoryDrug.getCategory_drug());
        holder.setClickListener(new SickAdapter.ItemClickListener() {
            @Override
            public void onClickItem(int pos) {
                openDetailActivity(categoryDrug.getCategory_drug());
            }

            @Override
            public void onLongClickItem(int pos) {

            }
        });

    }
    private void openDetailActivity(String...details)
    {
      //  Intent i=new Intent(context, ListCategoryDrug.class);

    //    i.putExtra("List_drug",details[0]);

        // i.putExtra("DESC_KEY",details[1]);
        //  i.putExtra("PROP_KEY",details[2]);

       // context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listdr.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private SickAdapter.ItemClickListener mListener;
        TextView name;


        // Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
         //   name=  itemView.findViewById(R.id.tvListListDrug);
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
