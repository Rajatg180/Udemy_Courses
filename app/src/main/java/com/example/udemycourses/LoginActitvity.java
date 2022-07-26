package com.example.udemycourses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActitvity extends AppCompatActivity {

     private TextInputEditText userNameEdt,pwdEdt;
     private Button loginBtn;
     private ProgressBar loadingPB;
     private TextView registerTV;
     private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actitvity);
        userNameEdt =findViewById(R.id.idEdUserName);
        pwdEdt =findViewById(R.id.idEdPassword);
        loginBtn =findViewById(R.id.btnLogin);
        registerTV =findViewById(R.id.registerTv);
        loadingPB =findViewById(R.id.idProgressBar);
        mAuth=FirebaseAuth.getInstance();

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActitvity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=userNameEdt.getText().toString();
                String pwd=pwdEdt.getText().toString();
                if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginActitvity.this, "Please Fill  Blank Space", Toast.LENGTH_SHORT).show();
                    return;

                }
                else {
                    mAuth.signInWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(LoginActitvity.this, "User Login Successful..", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(LoginActitvity.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else {
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(LoginActitvity.this, "Fail To Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
    //checking whether user is already login or not
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            Intent i=new Intent(LoginActitvity.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }
    }
}