package com.caption.pcp.captionia;

/**
 * Created by PCP on 24-12-2018.
 */

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BioRecyclerViewAdapter extends RecyclerView.Adapter<BioRecyclerViewAdapter.ViewHolder>  {

    Context context;
    List<UploadCaption> MainImageUploadInfoList,filterList;


    public BioRecyclerViewAdapter(Context context, List<UploadCaption> TempList) {

        this.MainImageUploadInfoList = TempList;
        this.filterList = TempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bio_recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final UploadCaption uploadCaption = MainImageUploadInfoList.get(position);


        holder.cat.setText(uploadCaption.getCaption_name());
        holder.ct.setText(uploadCaption.getCategory_name());

        holder.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(uploadCaption.getCaption_name());
                //Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show();

                Snackbar.make(v,"Copied to clipboard",Snackbar.LENGTH_SHORT).show();
            }
        });


        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(context);
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,uploadCaption.getCaption_name());
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share caption...");
               context.startActivity(Intent.createChooser(shareIntent, "Share Caption..."));
            }
        });

        holder.preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cap = uploadCaption.getCaption_name();
                PreviewDialogClass cdd=new PreviewDialogClass(context,cap);
                cdd.show();
                //Snackbar.make(v,"Preview..",Snackbar.LENGTH_SHORT).show();

            }
        });


        holder.like.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                User user = new User(context);
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.instagram.android");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, uploadCaption.getCaption_name());
                try {
                    context.startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    //Toast.makeText(context, "Whatsapp is not installed...", Toast.LENGTH_SHORT).show();
                    Snackbar.make(v,"Instagram is not installed...",Snackbar.LENGTH_SHORT).show();
                }



            }
        });

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cat,ct;
        public ImageView copy,share,wp,like;
        Button preview;
        public ViewHolder(View itemView ) {

            super(itemView);
            preview = (Button) itemView.findViewById(R.id.preview);
            cat = (TextView) itemView.findViewById(R.id.captions_name);
            ct = (TextView)itemView.findViewById(R.id.ct);
            copy = (ImageView)itemView.findViewById(R.id.copy);
            share = (ImageView)itemView.findViewById(R.id.share);
            wp = (ImageView)itemView.findViewById(R.id.insta);
            like=(ImageView)itemView.findViewById(R.id.like);

        }


    }



}