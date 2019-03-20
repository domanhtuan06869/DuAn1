package com.cao.nang.duan;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.cao.nang.duan.base.Base;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class resgistration extends Base {
    private EditText edtEmail;
    private EditText edtPassword ,getEdtPassword2;
    CheckBox checkBox;
    String email, password, password2;
    boolean cbdk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistration);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        getEdtPassword2=findViewById(R.id.edtPassword2);
        checkBox=findViewById(R.id.cbDongy);



    }
///??ng kí tài kho?n vào group
    public void DangKi(View view) {
        email=edtEmail.getText().toString();
        password=edtPassword.getText().toString();
        password2=getEdtPassword2.getText().toString();
        cbdk=checkBox.isChecked();
        if(email.equals("")){
            showToast("Không để trống em");
        }
        else if(password.equals("")||password2.equals("")){
            showToast("không để tróng mk");
        }
        else if(password.length()<6){
            showToast("hơn 6kt?");
        }
        else if(cbdk!=true){
            showToast("vui lòng đồng ý đk");

        }
        else if(!password.equals(password2)){
            showToast("mk chưa khớp");
        }else{

            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(resgistration.this,"định dạng sai",Toast.LENGTH_LONG).show();
                                Log.e("ero", String.valueOf(task.getException()));
                            }
                            else{
                                Toast.makeText(resgistration.this,"tài khoản",Toast.LENGTH_LONG).show();
                                classintent(LoginGroup.class);

                            }
                        }
                    });

        }




    }
}
