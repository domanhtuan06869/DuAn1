package com.cao.nang.duan.chatgroup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cao.nang.duan.R;
import com.cao.nang.duan.base.Base;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginGroup  extends Base {
    private EditText edtEmaildg;
    private EditText edtpass;
    private TextView chuacotk;
    private CheckBox cbNhomk;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String USERNAME = "userNameKey";
    public static final String PASS = "passKey";
    public static final String REMEMBER = "remember";
    SharedPreferences sharedpreferences;
    private String password, email;
    public  String emal12;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_login);
        edtEmaildg = (EditText) findViewById(R.id.edtEmaildg);
        edtpass = (EditText) findViewById(R.id.edtpass);
        chuacotk = (TextView) findViewById(R.id.chuacotk);
        cbNhomk = (CheckBox) findViewById(R.id.cbNhomk);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        chuacotk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classintent(Resgistration_activity.class);
            }
        });
        progressBar=findViewById(R.id.spin_kit);
        FadingCircle wave=new FadingCircle();
        progressBar.setIndeterminateDrawable(wave);
        progressBar.setVisibility(View.INVISIBLE);
        loadData();

    }


    public void Dangnhap(View view) {

        email=edtEmaildg.getText().toString();
        password=edtpass.getText().toString();
        if(email.equals("")){

            showToast("không để trống email ");
        }
        else if(password.equals("")){
            showToast("không để trống mật khẩu");
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                showToast("tài khoản  không chính xác");
                            }
                            else
                            {

                        openDetailActivity(email);
                                showToast("thành công");
                                if(cbNhomk.isChecked())saveData(email,password);
                                else clearData();
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
        }



    }
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(this,BangTinActyvity .class);
        i.putExtra("Email",details[0]);
        this.startActivity(i);
    }
    private void loadData() {
        if(sharedpreferences.getBoolean(REMEMBER,false)) {
            edtEmaildg.setText(sharedpreferences.getString(USERNAME, ""));
            edtpass.setText(sharedpreferences.getString(PASS, ""));
            cbNhomk.setChecked(true);
        }
        else
            cbNhomk.setChecked(false);

    }
    private void clearData() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    private void saveData(String username, String Pass) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASS, Pass);
        editor.putBoolean(REMEMBER,cbNhomk.isChecked());
        editor.commit();
    }
}
