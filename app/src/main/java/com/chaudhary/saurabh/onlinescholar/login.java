package com.chaudhary.saurabh.onlinescholar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private EditText et1,et2;
    private Button b1,b2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et1=findViewById(R.id.emailText);
        et2=findViewById(R.id.passwordText);
        b1=findViewById(R.id.loginB);
        b2=findViewById(R.id.signupB);
        auth=FirebaseAuth.getInstance();



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final String user = et1.getText().toString();
                    final String pass = et2.getText().toString();
                    auth.signInWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(login.this, UserDetailsActivity.class));
                                    } else {
                                        Toast.makeText(login.this, "Invalid Password or Username", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                }
                catch(IllegalArgumentException e)
                {
                    Toast.makeText(login.this, "Enter Details", Toast.LENGTH_LONG).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final String user = et1.getText().toString();
                    final String pass = et2.getText().toString();
                    auth.createUserWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(login.this, "Registration SuccessFul", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(login.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                catch(IllegalArgumentException e)
                {
                    Toast.makeText(login.this, "Enter Details", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}