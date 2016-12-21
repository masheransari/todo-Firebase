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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null)
        {
            startActivity(new Intent(login.this,MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

//        Toolbar toolbar

        inputEmail = (EditText)findViewById(R.id.email_login);
        inputPassword = (EditText)findViewById(R.id.password_login);
        progressBar = (ProgressBar)findViewById(R.id.progressBar_login);
        btnSignIn = (Button)findViewById(R.id.btn_login_login);
        btnSignUp = (Button)findViewById(R.id.sign_up_button_login);
        btnResetPassword = (Button)findViewById(R.id.btn_reset_password_login);

        auth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,signup.class));
            }
        });
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,resetPsk.class));
                finish();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(login.this, ""+inputEmail.getText()+" and psk = "+inputPassword.getText(), Toast.LENGTH_SHORT).show();
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (!task.isSuccessful())
                        {
                            if (password.length()<6)
                            {
                                inputPassword.setError(getString(R.string.minimum_password));
                            }
                            else
                            {
                                Toast.makeText(login.this, ""+getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Intent intent = new Intent(login.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }
}
