package com.example.arsalankhan.firebaseuserregisteration;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btn_registerUser;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_registerUser= (Button) findViewById(R.id.button);
        firebaseAuth=FirebaseAuth.getInstance();

        btn_registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {

        EditText et_email,et_password;

        et_email= (EditText) findViewById(R.id.et_email);
        et_password= (EditText) findViewById(R.id.et_password);

        String email=et_email.getText().toString().trim();
        String password=et_password.getText().toString().trim();

        //validation

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "User Successfully Register", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this, "User Registration Fail, try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }else{
            Toast.makeText(this, "Fill the Fields First", Toast.LENGTH_SHORT).show();
        }
    }
}
