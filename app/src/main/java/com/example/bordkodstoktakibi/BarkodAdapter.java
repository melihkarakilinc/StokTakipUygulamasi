package com.example.bordkodstoktakibi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


        //cardview üzerinde ki textviewlere değerleri atadık
        holder.txt_stok_ad.setText(stk.getStok_adi());

        holder.txt_barkod_no.setText(stk.getBarkod_no());

        holder.txt_adet.setText(stk.getAdet());

        holder.txt_id.setText(stk.getStok_id());





        //cardview e tıklandığında
        holder.satirCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        //add image tıklanıldığında (stoğa ekleme yapacak)
        holder.addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //animasyonlar
                Animation scale_up,scale_down;
                scale_up= AnimationUtils.loadAnimation(mContext,R.anim.scale_up);
                scale_down= AnimationUtils.loadAnimation(mContext,R.anim.scale_down);
                holder.addimage.startAnimation(scale_up);
                holder.addimage.startAnimation(scale_down);

                //firebase database tanımlamaları
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Kisiler");

                String id=holder.txt_id.getText().toString();
                String adet=holder.txt_adet.getText().toString();

                //alert ekledik
                Dialog dialog = new Dialog(mContext);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.alertcostomdetay);
                dialog.show();

                //buraya edt ve buton eklenecek
                EditText edt_alert_stok_ekle = dialog.findViewById(R.id.edt_alert_stok_ekle);
                Button alert_btn_ekle2 = dialog.findViewById(R.id.alert_btn_ekle2);
                Button alert_btn_iptal = dialog.findViewById(R.id.alert_btn_iptal);

                //alertde kaydet butonuna tıklandığında
                alert_btn_ekle2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation scale_up,scale_down;
                        scale_up= AnimationUtils.loadAnimation(mContext,R.anim.scale_up);
                        scale_down= AnimationUtils.loadAnimation(mContext,R.anim.scale_down);
                        alert_btn_iptal.startAnimation(scale_up);
                        alert_btn_iptal.startAnimation(scale_down);
                        //eklenecek stok adedi girilmemiş ise
                        if (edt_alert_stok_ekle.getText().toString().equals("")){
                            Toast.makeText(mContext,"TÜM ALANLARI DOLDURUN",Toast.LENGTH_SHORT).show();
                        }
                        //eklenecek stok adedi girilmişse
                        else {
                            //firebase de güncelleme yapma
                            Map<String,Object> bilgiler =new HashMap<>();
                            Integer toplam = Integer.valueOf(edt_alert_stok_ekle.getText().toString()) + Integer.valueOf(adet);
                            Log.e("toplam",toplam.toString());
                            bilgiler.put("adet",toplam.toString());
                            myRef.child(id).updateChildren(bilgiler);
                            dialog.dismiss();
                            Toast.makeText(mContext,"STOK BAŞARI İLE EKLENDİ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //alert de iptal butonun abasılırsa
                alert_btn_iptal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //animasyonlar
                        Animation scale_up,scale_down;
                        scale_up= AnimationUtils.loadAnimation(mContext,R.anim.scale_up);
                        scale_down= AnimationUtils.loadAnimation(mContext,R.anim.scale_down);
                        alert_btn_iptal.startAnimation(scale_up);
                        alert_btn_iptal.startAnimation(scale_down);


                        //alerti kapatıyor
                        dialog.dismiss();
                    }
                });

            }
        });

        //çıkar imagine tıklandığında (stoktan çıkarma yapacak)
        holder.cikarimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //animasyonlar
                Animation scale_up,scale_down;
                scale_up= AnimationUtils.loadAnimation(mContext,R.anim.scale_up);
                scale_down= AnimationUtils.loadAnimation(mContext,R.anim.scale_down);
                holder.cikarimage.startAnimation(scale_up);
                holder.cikarimage.startAnimation(scale_down);

                //firebase database taımlamaları
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Kisiler");

                String id=holder.txt_id.getText().toString();
                String adet=holder.txt_adet.getText().toString();

                //alert ekledik
                Dialog dialog = new Dialog(mContext);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.alertcostomdetay);
                dialog.show();

                //buraya edt ve buton eklenecek
                EditText edt_alert_stok_ekle = dialog.findViewById(R.id.edt_alert_stok_ekle);
                Button alert_btn_ekle2 = dialog.findViewById(R.id.alert_btn_ekle2);
                Button alert_btn_iptal = dialog.findViewById(R.id.alert_btn_iptal);

                // alertde kaydet butonuna tıklanırsa
                alert_btn_ekle2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation scale_up,scale_down;
                        scale_up= AnimationUtils.loadAnimation(mContext,R.anim.scale_up);
                        scale_down= AnimationUtils.loadAnimation(mContext,R.anim.scale_down);
                        alert_btn_iptal.startAnimation(scale_up);
                        alert_btn_iptal.startAnimation(scale_down);
                        //çıkarılıcak stok adedi girilmemiş ise
                        if (edt_alert_stok_ekle.getText().toString().equals("")){
                            Toast.makeText(mContext,"TÜM ALANLARI DOLDURUN",Toast.LENGTH_SHORT).show();
                        }
                        //çıkarılacak stok adedi girilmiş ise
                        else {
                            //firebase de güncelleme yapma
                            Map<String,Object> bilgiler =new HashMap<>();
                            Integer toplam = Integer.valueOf(adet) - Integer.valueOf(edt_alert_stok_ekle.getText().toString())  ;
                            Log.e("toplam",toplam.toString());
                            bilgiler.put("adet",toplam.toString());
                            myRef.child(id).updateChildren(bilgiler);
                            dialog.dismiss();
                            Toast.makeText(mContext,"STOK BAŞARI İLE ÇIKARILDI",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //alert de iptal butonuna baasılırsa
                alert_btn_iptal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Animation scale_up,scale_down;
                        scale_up= AnimationUtils.loadAnimation(mContext,R.anim.scale_up);
                        scale_down= AnimationUtils.loadAnimation(mContext,R.anim.scale_down);
                        alert_btn_iptal.startAnimation(scale_up);
                        alert_btn_iptal.startAnimation(scale_down);


                        dialog.dismiss();
                    }
                });

            }
        });


    }

    @Override
    public int getItemCount() {
        return stokBilgiListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        //cardview üzerinde ki viewleri tanımladık
        private CardView satirCardView;
        private TextView txt_stok_ad;
        private TextView txt_barkod_no;
        private TextView txt_adet;
        private TextView txt_id;
        private ImageView addimage;
        private ImageView cikarimage;


        public CardTasarimTutucu(View itemView) {
            super(itemView);
            //cardview üzerinde ki viewleri yakaladık
            satirCardView = itemView.findViewById(R.id.satirCardView);
            txt_stok_ad = itemView.findViewById(R.id.txt_stok_ad);
            txt_barkod_no = itemView.findViewById(R.id.txt_barkod_no);
            txt_adet = itemView.findViewById(R.id.txt_adet);
            txt_id = itemView.findViewById(R.id.txt_id);
            addimage=itemView.findViewById(R.id.addimage);
            cikarimage=itemView.findViewById(R.id.cikarimage);
        }
    }


}
