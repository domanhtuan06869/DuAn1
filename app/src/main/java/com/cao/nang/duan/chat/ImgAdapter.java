package com.cao.nang.duan.chat;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cao.nang.duan.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImgAdapter extends RecyclerView.Adapter <ImgAdapter.MyViewHolder> {
    Context context;
    List<ImageUploadInfo> listdr;


    public ImgAdapter(Context c, List<ImageUploadInfo> list) {
        context = c;
        listdr = list;
    }

    @NonNull
    @Override
    public ImgAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ImgAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_itemview,viewGroup,false));
    }



    @Override
    public void onBindViewHolder(@NonNull ImgAdapter.MyViewHolder holder, int position) {
        final int i=position;
        final ImageUploadInfo imageUploadInfo=listdr.get(position);
        holder.name.setText(imageUploadInfo.getTitle());
        Picasso.get().load(imageUploadInfo.getImageURL()).into(holder.imageView);

        holder.setClickListener(new ImgAdapter.ItemClickListener() {
            @Override
            public void onClickItem(int pos) {
               openDetailActivity(imageUploadInfo.getTitle(),imageUploadInfo.getImageURL());
            }

            @Override
            public void onLongClickItem(int pos) {

            }
        });

    }
    private void openDetailActivity(String...details)
    {
       Intent i=new Intent(context, Main3Activity.class);

      i.putExtra("title",details[0]);

        i.putExtra("img",details[1]);


       context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listdr.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImgAdapter.ItemClickListener mListener;
        TextView name;
        ImageView imageView;


        // Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgView);
            name=itemView.findViewById(R.id.tvmess);
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

        public void setClickListener(ImgAdapter.ItemClickListener listener) {
            this.mListener = listener;
        }



    }


    public interface ItemClickListener {
        void onClickItem(int pos);

        void onLongClickItem(int pos);
    }
}

