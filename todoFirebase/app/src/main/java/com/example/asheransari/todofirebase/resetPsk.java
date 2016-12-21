package com.example.asheransari.todofirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class resetPsk extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnReset, btnBack;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_psk);

        inputEmail = (EditText)findViewById(R.id.email_reset);
        btnReset = (Button)findViewById(R.id.btn_reset_password_reset);
        btnBack = (Button)findViewById(R.id.btn_back_reset);
        progressBar = (ProgressBar)findViewById(R.id.progressBar_reset);

        auth = FirebaseAuth.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(resetPsk.this,login.class));
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(resetPsk.this, "Please Enter Your Email ../.", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.GONE);
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(resetPsk.this, "We have Sent You Instra=ucation to reserpassw", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(resetPsk.this, "Failed to send resert email id", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });


    }
}
