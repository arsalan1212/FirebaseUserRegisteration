package com.example.arsalankhan.firebaseuserregisteration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        //User is Already Login
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(Login.this,profile.class));
        }
    }



    public void Login(View view){

        EditText et_email= (EditText) findViewById(R.id.editText_email);
        EditText et_pass= (EditText) findViewById(R.id.et_pass);

        String email=et_email.getText().toString().trim();
        String pass=et_pass.getText().toString().trim();

        progressDialog.setMessage("Logining to User Account");
        progressDialog.show();
        if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass)){
            if(pass.length()<6){
                progressDialog.dismiss();
                et_pass.setError("password must be alteast 6 digits");
            }else{

                firebaseAuth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                    progressDialog.dismiss();
                                    finish();
                                    startActivity(new Intent(Login.this,profile.class));
                                }else{
                                    Toast.makeText(Login.this, "Login Error", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });
            }

        }else{
            Toast.makeText(this, "Please Fill the Field First", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }
}
