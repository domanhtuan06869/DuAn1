package com.cao.nang.duan.chatgroup.adaptergroup;

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
import com.cao.nang.duan.chatgroup.BangTinActyvity;
import com.cao.nang.duan.chatgroup.CommentGroup_actitvity;
import com.cao.nang.duan.chatgroup.modelgroup.ImageUploadInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImgAdapter extends RecyclerView.Adapter <ImgAdapter.MyViewHolder> {
    Context context;
    List<ImageUploadInfo> listdr;
    String email;



    public ImgAdapter(Context c, List<ImageUploadInfo> list,String email) {
        context = c;
        listdr = list;
        this.email=email;
    }

    @NonNull
    @Override
    public ImgAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ImgAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_itemviewbantin,viewGroup,false));
    }



    @Override
    public void onBindViewHolder(@NonNull ImgAdapter.MyViewHolder holder, int position) {
        final int i=position;
        final ImageUploadInfo imageUploadInfo=listdr.get(position);
        Picasso.get().load(imageUploadInfo.getImageURL()).into(holder.imageView);
        holder.title.setText(imageUploadInfo.getTitle());
        holder.email.setText(imageUploadInfo.getEmail());
        holder.date.setText(imageUploadInfo.getDate());
        holder.content.setText(imageUploadInfo.getContent());

        holder.setClickListener(new ImgAdapter.ItemClickListener() {
            @Override
            public void onClickItem(int pos) {
                BangTinActyvity bangTinActyvity= new BangTinActyvity();
               openDetailActivity(imageUploadInfo.getTitle(),imageUploadInfo.getImageURL(),imageUploadInfo.getContent(),email,imageUploadInfo.getDate(),imageUploadInfo.getEmail());
            }

            @Override
            public void onLongClickItem(int pos) {

            }
        });

    }
    private void openDetailActivity(String...details)
    {
       Intent i=new Intent(context, CommentGroup_actitvity.class);

      i.putExtra("title",details[0]);

        i.putExtra("img",details[1]);
        i.putExtra("content",details[2]);
        i.putExtra("Email",details[3]);
        i.putExtra("Date",details[4]);
        i.putExtra("Emaill",details[5]);


       context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return listdr.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImgAdapter.ItemClickListener mListener;
        TextView title,email,date,content;
        ImageView imageView;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgView);
            title=itemView.findViewById(R.id.tvmess);
            email=itemView.findViewById(R.id.emallchat);
            date=itemView.findViewById(R.id.ngaygio);
            content=itemView.findViewById(R.id.tvcontents);
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

