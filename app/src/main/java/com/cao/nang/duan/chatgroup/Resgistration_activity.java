package com.cao.nang.duan.chatgroup;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cao.nang.duan.R;
import com.cao.nang.duan.base.Base;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Resgistration_activity extends Base {
    private EditText edtEmail;
    private EditText edtPassword ,getEdtPassword2;
    CheckBox checkBox;
    String email, password, password2;
    boolean cbdk;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_resgistration);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        getEdtPassword2=findViewById(R.id.edtPassword2);
        checkBox=findViewById(R.id.cbDongy);
        progressBar=findViewById(R.id.spin_kitres);
        FadingCircle wave=new FadingCircle();
        progressBar.setIndeterminateDrawable(wave);
        progressBar.setVisibility(View.INVISIBLE);



    }
///đăng kí
    public void DangKi(View view) {
        email=edtEmail.getText().toString();
        password=edtPassword.getText().toString();
        password2=getEdtPassword2.getText().toString();
        cbdk=checkBox.isChecked();
        if(email.equals("")){
            showToast("Không để trống email");
        }
        else if(password.equals("")||password2.equals("")){
            showToast("không để tróng mật khẩu");
        }
        else if(password.length()<6){
            showToast("hơn 6kt?");
        }
        else if(cbdk!=true){
            showToast("vui lòng đồng ý với điều khoản của chúng tôi");

        }
        else if(!password.equals(password2)){
            showToast("mật khẩu chưa khớp");
        }else{
            progressBar.setVisibility(View.VISIBLE);
            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Resgistration_activity.this,"định dạng sai hoặc email đã tồn tại",Toast.LENGTH_LONG).show();
                                Log.e("ero", String.valueOf(task.getException()));
                            }
                            else{
                                Toast.makeText(Resgistration_activity.this,"đăng kí tài khoản thành công",Toast.LENGTH_LONG).show();
                                classintent(LoginGroup.class);
                                progressBar.setVisibility(View.INVISIBLE);


                            }
                        }
                    });

        }




    }
}
