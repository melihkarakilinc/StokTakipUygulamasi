package com.example.bordkodstoktakibi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
public class MainActivity extends AppCompatActivity {
    private Button button,btn_urunler;
    public String stokadi,stokadedi,kodsonucu;
    Animation scale_up,scale_down;

    private TextView text_qr_code_sonuc,txt_sonuc,txt_code_kind,txt_qr_code_kind_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;
        button = (Button) findViewById(R.id.button);
        btn_urunler = (Button) findViewById(R.id.btn_urunler);
        txt_sonuc = (TextView) findViewById(R.id.txt_sonuc);
        text_qr_code_sonuc = (TextView) findViewById(R.id.qr_code_sonucu);
        txt_code_kind = (TextView) findViewById(R.id.txt_code_kind);
        txt_qr_code_kind_result = (TextView) findViewById(R.id.txt_qr_code_kind_result);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Kisiler");


        scale_up= AnimationUtils.loadAnimation(this,R.anim.scale_up);
        scale_down= AnimationUtils.loadAnimation(this,R.anim.scale_down);




            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    button.startAnimation(scale_up);
                    button.startAnimation(scale_down);
                    if (button.getText().equals("TARA")) {
                        //Bu activity içinde çalıştırıyoruz.
                        IntentIntegrator integrator = new IntentIntegrator(activity);
                        //Kütüphanede bir kaç kod tipi var biz hepsini tarayacak şekilde çalıştırdık.
                        //integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                        //şeklindede sadece qr code taratabilirsiniz.
                        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                        //Kamera açıldığında aşağıda yazı gösterecek
                        integrator.setPrompt("TARA");
                        //telefonun kendi kamerasını kullandırıcaz
                        integrator.setCameraId(0);
                        //okuduğunda 'beep' sesi çıkarır
                        integrator.setBeepEnabled(true);
                        //okunan barkodun image dosyasını kaydediyor
                        integrator.setBarcodeImageEnabled(false);
                        //scan başlatılıyor
                        integrator.initiateScan();


                        //
                    }
                        if (button.getText().equals("KAYDET")) {

                            //SQL KAYDETME


                            Dialog dialog = new Dialog(MainActivity.this);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.setContentView(R.layout.alertcostom);
                            dialog.show();



                            //buraya edt ve buton eklenecek
                            EditText edt_alert_stokad = dialog.findViewById(R.id.edt_alert_stokad);
                            stokadi = edt_alert_stokad.getText().toString();
                            EditText edt_alert_stok_adedi = dialog.findViewById(R.id.edt_alert_stok_adedi);
                            stokadedi = edt_alert_stok_adedi.getText().toString();
                            Button alert_btn_kaydet = dialog.findViewById(R.id.alert_btn_kaydet);
                            Button alert_btn_iptal = dialog.findViewById(R.id.alert_btn_iptal);





                            alert_btn_kaydet.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    alert_btn_kaydet.startAnimation(scale_up);
                                    alert_btn_kaydet.startAnimation(scale_down);
                                    // kaydet butonuna basıldığında

                                    if (edt_alert_stok_adedi.getText().toString().equals("") || edt_alert_stokad.getText().toString().equals("")){
                                        Toast.makeText(MainActivity.this,"TÜM ALANLARI DOLDURUN",Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        String key = myRef.push().getKey();

                                        StokBilgi stk=new StokBilgi(key,kodsonucu,edt_alert_stok_adedi.getText().toString().trim(),(edt_alert_stokad.getText().toString().trim().toUpperCase()));
                                        myRef.push().setValue(stk);
                                        dialog.dismiss();
                                        button.setText("TARA");
                                        txt_sonuc.setText("QR KOD SONUC");
                                        Toast.makeText(MainActivity.this,"KAYIT İŞLEMİ BAŞARILI",Toast.LENGTH_SHORT).show();
                                    }



                                }
                            });

                            alert_btn_iptal.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    alert_btn_iptal.startAnimation(scale_up);
                                    alert_btn_iptal.startAnimation(scale_down);
                                    // iptal butonuna basıldığında

                                    dialog.dismiss();


                                }
                            });

                            //


                        }
                }
            });



//
//                            View tasarim = getLayoutInflater().inflate(R.layout.alertcostom,null);
//                            AlertDialog.Builder ad= new AlertDialog.Builder(MainActivity.this);
//                            ad.setView(tasarim);
//                            ad.create().show();
//
//                            //buraya edt ve buton eklenecek
//                            EditText edt_alert_stokad=tasarim.findViewById(R.id.edt_alert_stokad);
//                            stokadi=edt_alert_stokad.getText().toString();
//                            EditText edt_alert_stok_adedi=tasarim.findViewById(R.id.edt_alert_stok_adedi);
//                            stokadedi=edt_alert_stok_adedi.getText().toString();
//                            Button alert_btn_kaydet=tasarim.findViewById(R.id.alert_btn_kaydet);
//                            Button alert_btn_iptal=tasarim.findViewById(R.id.alert_btn_iptal);
//
//
//                            alert_btn_kaydet.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    // kaydet butonuna basıldığında
//                                }
//                            });
//
//                            alert_btn_iptal.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    // iptal butonuna basıldığında
//                                    Toast.makeText(MainActivity.this, "İptal'e tıklandı", Toast.LENGTH_SHORT).show();
//
//
//                                }
//                            });
//
//                            //
//
//
//
//
//                        }
//                }
//            });



        btn_urunler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_urunler.startAnimation(scale_up);
                btn_urunler.startAnimation(scale_down);

                //ürünleri giriş
                Intent ıntent=new Intent(MainActivity.this,UrunlerActivity.class);
                startActivity(ıntent);

            }
        });
            }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Kütüphane okuduktan sonra bu metodla bize result döndürüyor.
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                text_qr_code_sonuc.setText("Kod Sonucu:");
               txt_sonuc.setText("Qr Code Bulunamadı.");
                txt_code_kind.setText("Kod Türü:");
                txt_qr_code_kind_result.setText("Bulunamadı.");
            } else {
                text_qr_code_sonuc.setText("Kod Sonucu:");
                txt_sonuc.setText(result.getContents());
                kodsonucu=txt_sonuc.getText().toString();
                txt_code_kind.setText("Kod Türü:");
                txt_qr_code_kind_result.setText(result.getFormatName());
                button.setText("KAYDET");
            }
        }
    }
}