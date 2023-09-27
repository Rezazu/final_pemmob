package com.example.project_akhir_redon_daffa.Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_akhir_redon_daffa.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText ed1, ed2;
    Button bt1, bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        ed1 = (EditText) findViewById(R.id.tx_emaillog);
        ed2 = (EditText) findViewById(R.id.tx_passwordlog);
        bt1 = (Button) findViewById(R.id.btn_login);
        bt2 = (Button) findViewById(R.id.btn_regis);

        bt1.setOnClickListener(View -> {
            String email = ed1.getText().toString();
            String password = ed2.getText().toString();

            if(TextUtils.isEmpty(email)){
                Toast.makeText(Login.this, "e-mail is required", Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password)){
                Toast.makeText(Login.this, "password is required", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((task) -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(Login.this, "Sign-In Failed", Toast.LENGTH_SHORT).show();
                }
            });
        });

        bt2.setOnClickListener(View -> {
            Intent intent2 = new Intent (Login.this, Regis.class);
            startActivity(intent2);
        });
    }
}