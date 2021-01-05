package com.example.bordkodstoktakibi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UrunlerActivity extends AppCompatActivity {

    private RecyclerView StokRv;
    private ArrayList<StokBilgi> stokBilgi;
    private BarkodAdapter adapter;
    private  FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<StokBilgi> stokBilgiListe;
    private EditText edt_arama;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urunler);
        edt_arama = (EditText) findViewById(R.id.edt_arama);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Kisiler");

        StokRv = findViewById(R.id.StokRv);

        StokRv.setHasFixedSize(true);
        StokRv.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));






        edt_arama.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if (s.equals("")){
                    stokbilgiler();
                }
                else {
                    stokbilgilerArama(s.toString().toUpperCase());

                }

            }
        });



        stokBilgiListe =new ArrayList<>();
        adapter = new BarkodAdapter(this,stokBilgiListe);

        StokRv.setAdapter(adapter);
        stokbilgiler();

    }

    public void stokbilgiler(){


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                stokBilgiListe.clear();
                for (DataSnapshot d:snapshot.getChildren()){


                    StokBilgi stokBilgi = d.getValue(StokBilgi.class);
                    key = d.getKey();
                    stokBilgi.setStok_id(key);
                    stokBilgiListe.add(stokBilgi);



                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void stokbilgilerArama(String aranankelime){


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                stokBilgiListe.clear();
                for (DataSnapshot d:snapshot.getChildren()){


                    StokBilgi stokBilgi = d.getValue(StokBilgi.class);

                    if (stokBilgi.getStok_adi().contains(aranankelime)){

                        String key = d.getKey();
                        stokBilgi.setStok_id(key);
                        stokBilgiListe.add(stokBilgi);

                        Log.e("KEY",key);

                    }
                    else if (stokBilgi.getBarkod_no().contains(aranankelime)){

                        String key = d.getKey();
                        stokBilgi.setStok_id(key);
                        stokBilgiListe.add(stokBilgi);

                        Log.e("KEY",key);
                    }



                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}