package com.example.project_akhir_redon_daffa.Main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.project_akhir_redon_daffa.R;

import static android.text.TextUtils.isEmpty;

public class EditData extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;

    EditText edImage;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    String tx_ImageURL;
    boolean ImageCheck = false;

    ImageButton ib1;
    Button b1;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        ed2 = (EditText) findViewById(R.id.new_tanggal);
        ed3 = (EditText) findViewById(R.id.new_details);
        ed4 = (EditText) findViewById(R.id.new_website);
        ib1 = (ImageButton) findViewById(R.id.btn_upfoto);
        b1 = (Button) findViewById(R.id.btn_web);

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogForm();
            }
        });
        getData();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_tanggal = ed2.getText().toString();
                String new_details = ed3.getText().toString();
                String new_web = ed4.getText().toString();

                if(isEmpty(new_tanggal) || isEmpty(new_details)
                    || isEmpty(new_web)){
                    Toast.makeText(EditData.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }
                else if (ImageCheck != true) {
                    Toast.makeText(EditData.this, "Belum submit foto", Toast.LENGTH_SHORT).show();
                } else {
                    updateBeasiswa(new_tanggal, new_details, new_web, tx_ImageURL);
                }
            }
        });
    }
    private void getData(){
        final String getTanggal = getIntent().getExtras().getString("tanggal");
        final String getDetails = getIntent().getExtras().getString("details");
        final String getWeb = getIntent().getExtras().getString("web");
        ed2.setText(getTanggal);
        ed3.setText(getDetails);
        ed4.setText(getWeb);
    }
    private void updateBeasiswa(String tanggal, String details, String web, String foto){
        String getKey = getIntent().getExtras().getString("key");
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("beasiswa/"+getKey);
        reference.child("tanggal").setValue(tanggal);
        reference.child("details").setValue(details);
        reference.child("web").setValue(web);
        reference.child("foto").setValue(foto);
        Toast.makeText(EditData.this, "Data berhasil diubah!", Toast.LENGTH_SHORT).show();
        Intent intent2 = new Intent (EditData.this, MainActivity.class);
        startActivity(intent2);

    }
    private void DialogForm() {
        dialog = new AlertDialog.Builder(EditData.this);
        inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_alert, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Set your Image with URL!");
        edImage = (EditText) dialogView.findViewById(R.id.tx_ImageUrl);
        final String getFoto = getIntent().getExtras().getString("foto");
        edImage.setText(getFoto);
        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ImageCheck = true;
                tx_ImageURL    = edImage.getText().toString();
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}