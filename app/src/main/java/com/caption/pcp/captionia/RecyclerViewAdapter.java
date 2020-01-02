package com.caption.pcp.captionia;

/**
 * Created by PCP on 24-12-2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    Context context;
    List<Category> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<Category> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view );

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,  int position) {

        final Category category = MainImageUploadInfoList.get(position);

        holder.cat.setText(category.getInsert());

        Glide.with(context).load(category.getUrl()).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context, "You clicked "+ category.getInsert(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, Captions.class);
                i.putExtra("head",category.getUrl());
                i.putExtra("category",category.getInsert());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cat;
        public ImageView imageView;
        public CardView cardView;
        public ViewHolder(View itemView) {

            super(itemView);

            cat = (TextView) itemView.findViewById(R.id.c_name);
            imageView = (ImageView) itemView.findViewById(R.id.c_logo);
            cardView = (CardView)itemView.findViewById(R.id.cardview1);

        }


    }


}