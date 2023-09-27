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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.project_akhir_redon_daffa.R;
public class AddData extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;

//  --AlertDialog
    EditText edImage;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    String tx_ImageURL;

    ImageButton ib1;
    Button b1;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        ed1 = (EditText) findViewById(R.id.new_nama);
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
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = ed1.getText().toString();
                String tanggal = ed2.getText().toString();
                String details = ed3.getText().toString();
                String web = ed4.getText().toString();
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("beasiswa/"+nama);

                reference.child("nama").setValue(nama);
                reference.child("tanggal").setValue(tanggal);
                reference.child("details").setValue(details);
                reference.child("foto").setValue(tx_ImageURL);
                reference.child("web").setValue(web);

                Intent intent2 = new Intent (AddData.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
    private void DialogForm() {
        dialog = new AlertDialog.Builder(AddData.this);
        inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_alert, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Set your Image with URL!");

        edImage = (EditText) dialogView.findViewById(R.id.tx_ImageUrl);

        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
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