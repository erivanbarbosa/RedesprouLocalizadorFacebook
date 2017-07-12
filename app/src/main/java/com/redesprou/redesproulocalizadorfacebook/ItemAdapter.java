package com.redesprou.redesproulocalizadorfacebook;

import android.content.Context;
import android.hardware.camera2.params.Face;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.redesprou.redesproulocalizadorfacebook.util.PlaceFieldsUtil;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Erivan on 18/06/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    public ItemAdapter(List<FacebookPageItem> facebookPageItems, Context context) {
        this.facebookPageItems = facebookPageItems;
        this.context = context;
    }

    private List<FacebookPageItem> facebookPageItems;
    private Context context;


    /*
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    } */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext() ).inflate(R.layout.facebook_page_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        final FacebookPageItem facebookPageItem = facebookPageItems.get(position);

        holder.textViewNome.setText(facebookPageItem.getNome());
        holder.textViewCategoria.setText(PlaceFieldsUtil.tratarCategoria(facebookPageItem.getCategoria()));
        holder.textViewMedia.setText( facebookPageItem.getMedia() + "" );


        String cover = facebookPageItem.getCapa();
        if( !cover.isEmpty() ) {
            Picasso.with(context).load(cover).resize(300,300)
                    .into(holder.imageView);
        }

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicou" + facebookPageItem.getNome(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return facebookPageItems.size();
    }

    public void removeAll() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNome;
        public TextView textViewCategoria;
        public TextView textViewMedia;
        public ImageView imageView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView ){
            super(itemView);

            textViewNome = (TextView) itemView.findViewById(R.id.facebookPageItemHead );
            textViewCategoria = (TextView) itemView.findViewById(R.id.facebookPageItemCategoria);
            textViewMedia = (TextView) itemView.findViewById(R.id.facebookPageItemMedia);
            imageView = (ImageView) itemView.findViewById(R.id.facebookPageItemImage);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.RelativeLayout);
        }
    }

        public void addPage(FacebookPageItem item ) {
            facebookPageItems.add( item );

            notifyDataSetChanged();
        }
}
