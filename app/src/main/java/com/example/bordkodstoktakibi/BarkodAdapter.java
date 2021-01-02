package com.example.bordkodstoktakibi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class BarkodAdapter extends RecyclerView.Adapter<BarkodAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<StokBilgi> stokBilgiListe;

    public BarkodAdapter(Context mContext, List<StokBilgi> stokBilgiListe) {
        this.mContext = mContext;
        this.stokBilgiListe = stokBilgiListe;
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarimi,parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(CardTasarimTutucu holder, int position) {
        final StokBilgi stk = stokBilgiListe.get(position);


        holder.txt_stok_ad.setText(stk.getStok_adi());

        holder.txt_barkod_no.setText(stk.getBarkod_no());

        holder.txt_adet.setText(stk.getAdet());

        holder.txt_id.setText(stk.getStok_id());





        holder.satirCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return stokBilgiListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardView satirCardView;
        private TextView txt_stok_ad;
        private TextView txt_barkod_no;
        private TextView txt_adet;
        private TextView txt_id;

        public CardTasarimTutucu(View itemView) {
            super(itemView);
            satirCardView = itemView.findViewById(R.id.satirCardView);
            txt_stok_ad = itemView.findViewById(R.id.txt_stok_ad);
            txt_barkod_no = itemView.findViewById(R.id.txt_barkod_no);
            txt_adet = itemView.findViewById(R.id.txt_adet);
            txt_id = itemView.findViewById(R.id.txt_id);
        }
    }


}
