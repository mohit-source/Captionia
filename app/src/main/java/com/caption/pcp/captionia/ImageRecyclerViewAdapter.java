package com.caption.pcp.captionia;

/**
 * Created by PCP on 24-12-2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ImageRecyclerViewAdapter extends RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder>  {

    Context context;
    List<ImageGetSet> MainImageUploadInfoList,filterList;


    public ImageRecyclerViewAdapter(Context context, List<ImageGetSet> TempList) {

        this.MainImageUploadInfoList = TempList;
        this.filterList = TempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ImageGetSet imageGetSet = MainImageUploadInfoList.get(position);


        Glide.with(context).load(imageGetSet.getImage_url()).into(holder.image);
        holder.ct.setText(imageGetSet.getCategory_name());


    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView ct;
        public ImageView image,download,wp,share;
        public ViewHolder(View itemView ) {

            super(itemView);

            ct = (TextView)itemView.findViewById(R.id.ct);
            image = (ImageView)itemView.findViewById(R.id.image);
            share = (ImageView)itemView.findViewById(R.id.share);
            wp = (ImageView)itemView.findViewById(R.id.wp);
            download=(ImageView)itemView.findViewById(R.id.image_download);

        }


    }



}