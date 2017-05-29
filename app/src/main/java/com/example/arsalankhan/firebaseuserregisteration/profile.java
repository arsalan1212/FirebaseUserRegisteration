package com.example.arsalankhan.firebaseuserregisteration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profile extends AppCompatActivity {

    TextView tv_welcome;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_welcome= (TextView) findViewById(R.id.tv_welcome);

        firebaseAuth=FirebaseAuth.getInstance();

        firebaseUser=firebaseAuth.getCurrentUser();

        //if user is not login
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,Login.class));
        }

        // showing welcome message

        tv_welcome.setText("Wellcome:  "+firebaseUser.getEmail());
    }

    public void ResetPassword(View view){

        EditText et_email= (EditText) findViewById(R.id.et_email_reset);
        String email=et_email.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            et_email.setError("Please Fill the Field");
        }else{
            firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(profile.this, "We have send Email to Reset Your Password", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(profile.this, "Password Reset Error,Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void changeEmail(View view){
        EditText et_email= (EditText) findViewById(R.id.et_email_reset);
        String email=et_email.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            et_email.setError("Please Fill the Field");
        }else{
            firebaseUser.updateEmail(email)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(profile.this, "Your Email Updated Successfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(profile.this, "Email Update Error, Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    public void changePassword(View view){
        EditText et_email= (EditText) findViewById(R.id.et_email_reset);
        String password=et_email.getText().toString().trim();
        if(TextUtils.isEmpty(password)){
            et_email.setError("Please Fill the Field");
        }else{
          firebaseUser.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                  if(task.isSuccessful()){
                      Toast.makeText(profile.this, "Your Password Updated Successfully", Toast.LENGTH_SHORT).show();
                  }else{
                      Toast.makeText(profile.this, "Password Update Error, Try Again", Toast.LENGTH_SHORT).show();
                  }
              }
          });
        }
    }

    public void Logout(View view){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,Login.class));
    }
}
