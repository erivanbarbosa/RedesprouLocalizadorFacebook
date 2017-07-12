package com.redesprou.redesproulocalizadorfacebook;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Erivan on 12/07/2017.
 */

public class FacebookPageDialogActivity extends DialogFragment implements View.OnClickListener{

    FacebookPageItem facebookPageItem = null;

    ImageView imageView;
    TextView nome, categoria, engajamento, telefone, descricao, local;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facebook_page_dialog, null );

        imageView = (ImageView) view.findViewById(R.id.facebookPageDialogImage );
        nome = (TextView) view.findViewById(R.id.facebookPageDialogNome) ;
        categoria = (TextView) view.findViewById(R.id.facebookPageDialogCategoria) ;
        engajamento = (TextView) view.findViewById(R.id.facebookPageDialogEngajamento) ;
        telefone = (TextView) view.findViewById(R.id.facebookPageDialogTelefone) ;
        descricao = (TextView) view.findViewById(R.id.facebookPageDialogSobre) ;
        local = (TextView) view.findViewById(R.id.facebookPageDialogLocal) ;


        String cover = facebookPageItem.getCapa();
        if( !cover.isEmpty() ) {
            Picasso.with(getActivity()).load(cover).resize(300,300)
                    .into(imageView);
        }

        nome.setText( facebookPageItem.getNome());
        categoria.setText( facebookPageItem.getCategoria() );
        engajamento.setText( facebookPageItem.getEngajamento() );
        telefone.setText( facebookPageItem.getTelefone() );
        descricao.setText( facebookPageItem.getDescricao() );
        local.setText( facebookPageItem.getLocal() );


       // setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    public void setFacebookPageItem( FacebookPageItem item )
    {
        this.facebookPageItem = item;
    }
}
