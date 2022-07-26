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

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText userNameEdt,passEdt,confPasEdt;
    private Button regBtn;
    private ProgressBar loadingPB;
    private TextView loginTV;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userNameEdt=findViewById(R.id.idEdUserName);
        passEdt=findViewById(R.id.idEdPassword);
        confPasEdt=findViewById(R.id.idEdConfPassword);
        regBtn=findViewById(R.id.btnRegister);
        loginTV=findViewById(R.id.TVtext);
        mAuth=FirebaseAuth.getInstance();
        loadingPB=findViewById(R.id.idProgressBar);
        //moving to next activity
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterActivity.this,LoginActitvity.class);
                startActivity(i);
            }
        });
        //Registering User for first time
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                String userName=userNameEdt.getText().toString();
                String pwd=passEdt.getText().toString();
                String confpwd=confPasEdt.getText().toString();
                if(!pwd.equals(confpwd)){
                    Toast.makeText(RegisterActivity.this, "Please Check Both Password Once !", Toast.LENGTH_SHORT).show();

                }
                else if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(confpwd)){
                    Toast.makeText(RegisterActivity.this, "Please Fill Blank Space", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "User Registered Successfully..", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(RegisterActivity.this,LoginActitvity.class);
                                startActivity(i);
                                //finishing current activity i.e Registration
                                finish();
                            }
                            else {
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(RegisterActivity.this, "Fail To Register User", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

            }
        });


    }
}