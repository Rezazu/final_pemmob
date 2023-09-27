package com.example.project_akhir_redon_daffa.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.project_akhir_redon_daffa.R;
import com.example.project_akhir_redon_daffa.RecycleView.Adapter;
import com.example.project_akhir_redon_daffa.RecycleView.Beasiswa;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Adapter.FirebaseDataListener{
    Adapter MyAdapter;
    RecyclerView rv1;
    List<Beasiswa> BeasiswaList;
    ImageButton ib1;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BeasiswaList = new ArrayList<>();
        ib1 = (ImageButton) findViewById(R.id.btn_add);
        getDataFB();
        initRecycleView();

        ib1.setOnClickListener(View -> {
            Intent intent2 = new Intent (MainActivity.this, AddData.class);
            startActivity(intent2);
        });
    }
    private void getDataFB(){
        reff = FirebaseDatabase.getInstance().getReference();
        reff.child("beasiswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BeasiswaList = new ArrayList<>();
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    Beasiswa Bea = snapshot1.getValue(Beasiswa.class);
                    Bea.setKey(snapshot1.getKey());
                    BeasiswaList.add(Bea);
                }
                MyAdapter = new Adapter(BeasiswaList, MainActivity.this);
                rv1.setAdapter(MyAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initRecycleView(){
        rv1 = (RecyclerView) findViewById(R.id.RV);
        MyAdapter = new Adapter(BeasiswaList,this);
        rv1.setAdapter(MyAdapter);
        rv1.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onDeleteData(Beasiswa beasiswa, int position) {
        if(reff != null){
            reff.child("beasiswa").child(beasiswa.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(MainActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}