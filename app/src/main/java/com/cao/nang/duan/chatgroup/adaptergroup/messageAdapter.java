package com.cao.nang.duan.chatgroup.adaptergroup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cao.nang.duan.R;
import com.cao.nang.duan.chatgroup.modelgroup.Email;
import com.cao.nang.duan.chatgroup.modelgroup.Tomessage;

import java.util.List;

public class messageAdapter  extends RecyclerView.Adapter <messageAdapter.MyViewHolder> {
    Context context;
    List<Tomessage> listdr;


    public messageAdapter(Context c, List<Tomessage> list) {
        context = c;
        listdr = list;
    }

    @NonNull
    @Override
    public messageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new messageAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_comment,viewGroup,false));
    }



    @Override
    public void onBindViewHolder(@NonNull messageAdapter.MyViewHolder holder, int position) {
        final int i=position;
        final Tomessage tomessage=listdr.get(position);
        Email email =new Email() ;
       holder.message.setText("\n"+tomessage.getMessage());
       holder.email.setText(tomessage.getEmail());
       holder.date.setText(tomessage.getDate());

        holder.setClickListener(new messageAdapter.ItemClickListener() {
            @Override
            public void onClickItem(int pos) {

                // openDetailActivity(categoryDrug.getCategory_drug());
            }

            @Override
            public void onLongClickItem(int pos) {

            }
        });

    }
    private void openDetailActivity(String...details)
    {
        // Intent i=new Intent(context, .class);

        //  i.putExtra("List_drug",details[0]);

        // i.putExtra("DESC_KEY",details[1]);
        //  i.putExtra("PROP_KEY",details[2]);

        // context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listdr.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private messageAdapter.ItemClickListener mListener;
        TextView message,email, date;



        // Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            email=itemView.findViewById(R.id.tvcmtemail);
            date=itemView.findViewById(R.id.dateitemcmt);
            message=itemView.findViewById(R.id.tvcontencmtitem);

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

        public void setClickListener(messageAdapter.ItemClickListener listener) {
            this.mListener = listener;
        }



    }


    public interface ItemClickListener {
        void onClickItem(int pos);

        void onLongClickItem(int pos);
    }

}
