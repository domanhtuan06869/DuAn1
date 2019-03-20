package com.cao.nang.duan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cao.nang.duan.base.Base;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginGroup  extends Base {
    private EditText edtEmaildg;
    private EditText edtpass;
    private TextView chuacotk;

    private String password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmaildg = (EditText) findViewById(R.id.edtEmaildg);
        edtpass = (EditText) findViewById(R.id.edtpass);
        chuacotk = (TextView) findViewById(R.id.chuacotk);
        chuacotk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classintent(resgistration.class);
            }
        });
    }


    public void Dangnhap(View view) {
        email=edtEmaildg.getText().toString();
        password=edtpass.getText().toString();
        if(email.equals("")){

            showToast("không để trống email ");
        }
        else if(password.equals("")){
            showToast("không để trống mk");
        }
        else{
            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                showToast("tài  không chính xác");
                            }
                            else
                            {
                                classintent(activity_nhom_chat.class);;
                                showToast("thành công");
                            }
                        }
                    });
        }

    }
}
