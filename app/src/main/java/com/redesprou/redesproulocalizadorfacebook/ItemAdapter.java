package com.redesprou.redesproulocalizadorfacebook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        FacebookPageItem facebookPageItem = facebookPageItems.get(position);

        holder.textViewNome.setText(facebookPageItem.getNomePagina());
        holder.textViewDescricao.setText(facebookPageItem.getDescricaoPagina());
    }

    @Override
    public int getItemCount() {
        return facebookPageItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNome;
        public TextView textViewDescricao;

        public ViewHolder(View itemView ){
            super(itemView);

            textViewNome = (TextView) itemView.findViewById(R.id.facebookPageItemHead );
            textViewDescricao = (TextView) itemView.findViewById(R.id.facebookPageItemDesc);
        }


    }
}
