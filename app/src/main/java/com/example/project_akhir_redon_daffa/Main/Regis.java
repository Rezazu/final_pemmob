package com.example.project_akhir_redon_daffa.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.project_akhir_redon_daffa.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Regis extends AppCompatActivity {
    EditText ed1, ed2, ed3;
    CheckBox ch1, ch2;
    Button bt1;
    private static final String TAG = "EmailPassword";
    FirebaseAuth fAuth;
    boolean isAgree = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        fAuth = FirebaseAuth.getInstance();
        ed1 = (EditText) findViewById(R.id.tx_namareg);
        ed2 = (EditText) findViewById(R.id.tx_emailreg);
        ed3 = (EditText) findViewById(R.id.tx_passwordreg);
        bt1 = (Button) findViewById(R.id.btn_regis);
        ch1 = (CheckBox) findViewById(R.id.check_regis);
        ch2 = (CheckBox) findViewById(R.id.ch_reghide);

        ch1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (ch1.isChecked()){
                    isAgree = true;
                } else {
                    isAgree = false;
                }
            }
        });

        ch2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (ch2.isChecked()){
                    ed3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    ed3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed2.getText().toString();
                String password = ed3.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Regis.this, "e-mail is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Regis.this, "password is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(Regis.this, "password must be longer than 6 character", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(getApplicationContext(), "Anda Belum Setuju !!",
                            Toast.LENGTH_SHORT).show();
                }
                if (isAgree == true) {
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Regis.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Regis.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                            } else {
                                Toast.makeText(Regis.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Anda Belum Setuju !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}