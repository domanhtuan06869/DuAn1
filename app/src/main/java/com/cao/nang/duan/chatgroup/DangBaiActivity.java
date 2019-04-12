package com.cao.nang.duan.chatgroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cao.nang.duan.R;
import com.cao.nang.duan.chatgroup.adaptergroup.ImgAdapter;
import com.cao.nang.duan.chatgroup.modelgroup.ImageUploadInfo;
import com.cao.nang.duan.chatgroup.modelgroup.Message;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DangBaiActivity extends AppCompatActivity {

    private String  saveCurrentDate, saveCurrentTime;
    private TextView AddNewProductButton;
    private ImageView InputProductImage;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private TextView emalldangbai;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private DatabaseReference databaseReference;
    private ProgressDialog loadingBar;
    private List<ImageUploadInfo> imageUploadInfos=new ArrayList<>();
    private  String email;
    ImgAdapter adapter;

    private EditText title;
    String time24;
    private EditText content;
    private String chattitle;
    private String contents;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_bai_activity);
        this.initview();
         progressBar = (ProgressBar) findViewById(R.id.spin_kitdangbai);
        FadingCircle wave=new FadingCircle();
        progressBar.setIndeterminateDrawable(wave);
        progressBar.setVisibility(View.INVISIBLE);







        InputProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                OpenGallery();
            }
        });
        AddNewProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(ImageUri!=null) {

                    ValidateProductData();
                }else {
                 postnotimage();
                }
            }
        });


    }



    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputProductImage.setImageURI(ImageUri);
        }
    }


    private void ValidateProductData()
    {
/// check rỗng
        String title1=title.getText().toString();
        String content1=content.getText().toString();
       if (ImageUri == null) {
       }else  if (title1.equals("")||content1.equals("")){
           Toast.makeText(this, "Nhập nội dung cho tiêu đề và nội dung", Toast.LENGTH_SHORT).show();

       }
       else if(title1.equals(".")){
           Toast.makeText(this, "Tiêu đề không được có kí tự đặc biệt", Toast.LENGTH_SHORT).show();

       }
        else
      {

          StoreProductInformation();

        }
    }



    private void StoreProductInformation()
    {
        progressBar.setVisibility(View.VISIBLE);

 /// lấy time cho bài viết
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;

        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(DangBaiActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())

                        {
                    //thêm ảnh và bài viết
                            int day, month, year;
                            GregorianCalendar datetime = new GregorianCalendar();
                            String now = new SimpleDateFormat("hh:mm aa").format(new java.util.Date().getTime());
                            SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm aa");
                            SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
                            try {
                                 time24 = outFormat.format(inFormat.parse(now));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            day = datetime.get(Calendar.DAY_OF_MONTH);
                            month = datetime.get(Calendar.MONTH);
                            year = datetime.get(Calendar.YEAR);
                            String ngaythang="lúc "+time24+" ,"+day+"/"+month+"/"+year;
                            try {

                                chattitle = title.getText().toString();
                                contents=content.getText().toString();

                                ImageUploadInfo imageUploadInfo = new ImageUploadInfo(chattitle,contents, task.getResult().toString(), email, ngaythang);

                                // lấy id tự động
                                String ImageUploadId = ProductsRef.push().getKey();

                                // up loa bai viết
                                ProductsRef.child(ImageUploadId).setValue(imageUploadInfo);
                                databaseReference = FirebaseDatabase.getInstance().getReference().child(chattitle);
                                Message message = new Message("","","");
                                String id = databaseReference.push().getKey();
                                databaseReference.child(id).setValue(message);
                            }catch (Exception e){
                                Toast.makeText(DangBaiActivity.this, "tiêu đề yêu cầu không dấu và kí tự đặc biệt", Toast.LENGTH_SHORT).show();

                            }
                            Toast.makeText(DangBaiActivity.this, "cập nhập trạng thái thành công", Toast.LENGTH_SHORT).show();

                            finish();

                        }
                    }
                });
            }
        });


}
/// thêm bài viết để trống ảnh
public void  postnotimage(){
    String title1=title.getText().toString();
    String content1=content.getText().toString();
    if (title1.equals("")||content1.equals("")){
        Toast.makeText(DangBaiActivity.this, "Nhập nội dung cho tiêu đề và nội dung", Toast.LENGTH_SHORT).show();

    }else if(title1.equals(".")) {
        Toast.makeText(this, "Tiêu đề không được có kí tự đặc biệt", Toast.LENGTH_SHORT).show();
    }else{
        progressBar.setVisibility(View.VISIBLE);

        int day, month, year;
        GregorianCalendar datetime = new GregorianCalendar();
        String now = new SimpleDateFormat("hh:mm aa").format(new java.util.Date().getTime());
        SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm aa");
        SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
        try {
            time24 = outFormat.format(inFormat.parse(now));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = datetime.get(Calendar.DAY_OF_MONTH);
        month = datetime.get(Calendar.MONTH);
        year = datetime.get(Calendar.YEAR);
        String ngaythang="lúc "+time24+" ,"+day+"/"+month+"/"+year;
        try {

            chattitle = title.getText().toString();
            contents=content.getText().toString();

            ImageUploadInfo imageUploadInfo = new ImageUploadInfo(chattitle,contents, "https://firebasestoage/khongcoanh", email, ngaythang);

            String ImageUploadId = ProductsRef.push().getKey();

            ProductsRef.child(ImageUploadId).setValue(imageUploadInfo);
            databaseReference = FirebaseDatabase.getInstance().getReference().child(chattitle);
            Message message = new Message("","","");
            String id = databaseReference.push().getKey();
            databaseReference.child(id).setValue(message);
        }catch (Exception e){
            Toast.makeText(DangBaiActivity.this, "tiêu đề yêu cầu không dấu và kí tự đặc biệt", Toast.LENGTH_SHORT).show();

        }
        Toast.makeText(DangBaiActivity.this, "cập nhập trạng thái thành công", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.INVISIBLE);

        finish();

     }
    }


  private  void  initview(){
      ProductImagesRef = FirebaseStorage.getInstance().getReference().child("All_Image_Uploads/");
      ProductsRef = FirebaseDatabase.getInstance().getReference().child("All_Image_Uploads_Database");
      AddNewProductButton =findViewById(R.id.add_new_product);
      InputProductImage = (ImageView) findViewById(R.id.select_product_image);
      loadingBar = new ProgressDialog(this);
      title = (EditText) findViewById(R.id.title);
      content = (EditText) findViewById(R.id.content);
      Intent intent=getIntent();
      Bundle b=intent.getExtras();
      email=b.getString("Email");
      emalldangbai = (TextView) findViewById(R.id.emalldangbai);
      emalldangbai.setText(email);


  }


    public void huy(View view) {
        finish();
    }
}



