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
import com.cao.nang.duan.ScollDrug;
import com.cao.nang.duan.model.CategoryDrug;
import com.cao.nang.duan.ListCategoryDrug;
import com.cao.nang.duan.model.Drug;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.MyViewHolder> {
    Context context;
    List<Drug> listdr;


    public  DrugAdapter(Context c, List<Drug> list) {
        context = c;
        listdr = list;
    }

    @NonNull
    @Override
    public DrugAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DrugAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_drug,viewGroup,false));
    }



    @Override
    public void onBindViewHolder(@NonNull DrugAdapter.MyViewHolder holder, int position) {
        final int i=position;
        final Drug drug=listdr.get(position);
        holder.tvnameDrug.setText(drug.getName_drug());
        holder.setClickListener(new DrugAdapter.ItemClickListener() {
            @Override
            public void onClickItem(int pos) {
                openDetailActivity(drug.getName_drug(),drug.getPrice_drug(),drug.getIngredient_drug()
                ,drug.getAssign_drug(),drug.getContraindicated_drug(),drug.getUser_drug(),drug.getSide_efects(),drug.getAttention()
                );
            }

            @Override
            public void onLongClickItem(int pos) {

            }
        });

    }
    private void openDetailActivity(String...details)
    {
       Intent i=new Intent(context, ScollDrug.class);
     i.putExtra("Name_Drug",details[0]);
     i.putExtra("Price",details[1]);
     i.putExtra("Ingredient",details[2]);
     i.putExtra("Assign",details[3]);
     i.putExtra("Contraindicated",details[4]);
     i.putExtra("User",details[5]);
     i.putExtra("Effect",details[6]);
     i.putExtra("Attention",details[7]);

    context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listdr.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private DrugAdapter.ItemClickListener mListener;
        TextView tvnameDrug;


        // Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
          tvnameDrug=  itemView.findViewById(R.id.tvlistDrug);
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

        public void setClickListener(DrugAdapter.ItemClickListener listener) {
            this.mListener = listener;
        }



    }


    public interface ItemClickListener {
        void onClickItem(int pos);

        void onLongClickItem(int pos);
    }
}
