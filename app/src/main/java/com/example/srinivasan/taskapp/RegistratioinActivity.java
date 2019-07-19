package com.example.srinivasan.taskapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistratioinActivity extends AppCompatActivity {
    private EditText email;
    private EditText pass;
    private Button btnreg;
    private TextView login_txt;
    private FirebaseAuth mAuth;
    private ProgressDialog mDiagog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registratioin);
        mAuth = FirebaseAuth.getInstance();
        mDiagog = new ProgressDialog(this);
        email=findViewById(R.id.email_pass);
        pass=findViewById(R.id.password_reg);
        btnreg=findViewById(R.id.reg_btn);
        login_txt=findViewById(R.id.Login_txt);
        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });



       btnreg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            String mEmail = email.getText().toString().trim();
            String mPass = pass.getText().toString().trim();
            if(TextUtils.isEmpty(mEmail))
            {
                email.setError("Required Field");
                return;
            }
            if (TextUtils.isEmpty(mPass))
            {
                pass.setError("Required Field");
                return;
            }
            mDiagog.setMessage("Processing...");
            mDiagog.show();
            mAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful()){
                     Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_LONG).show();
                     startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                     mDiagog.dismiss();
                 }else
                 {
                     Toast.makeText(getApplicationContext(),"Problem",Toast.LENGTH_LONG).show();
                     mDiagog.dismiss();
                 }
                }
            });

           }
       });

    }
}
