package com.cao.nang.duan.chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cao.nang.duan.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainChat extends AppCompatActivity {

    private String CategoryName, Description, Price, Pname, saveCurrentDate, saveCurrentTime;
    private Button AddNewProductButton;
    private ImageView InputProductImage;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String productRandomKey, downloadImageUrl;
    private StorageReference ProductImagesRef;
    private DatabaseReference ProductsRef;
    private DatabaseReference databaseReference;
    private ProgressDialog loadingBar;
    private List<ImageUploadInfo> imageUploadInfos=new ArrayList<>();
    private  List<Tomessage>messageList=new ArrayList<>();
    messageAdapter messageAdapter;
    ImgAdapter adapter;
    private RecyclerView rclist;
    private EditText chat;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_bai);


     //   CategoryName = getIntent().getExtras().get("category").toString();
        ProductImagesRef = FirebaseStorage.getInstance().getReference().child("All_Image_Uploads/");
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("All_Image_Uploads_Database");
        rclist = (RecyclerView) findViewById(R.id.rclist);
        AddNewProductButton = (Button) findViewById(R.id.add_new_product);
        InputProductImage = (ImageView) findViewById(R.id.select_product_image);
        loadingBar = new ProgressDialog(this);
        chat = (EditText) findViewById(R.id.chat);



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
                ValidateProductData();
            }
        });
  ///getdataf();

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
      /*  Description = InputProductDescription.getText().toString();
        Price = InputProductPrice.getText().toString();
        Pname = InputProductName.getText().toString();*/

       if (ImageUri == null) {
            Toast.makeText(this, "Product image is mandatory...", Toast.LENGTH_SHORT).show();
       }
        else
      {
            StoreProductInformation();
        }
    }



    private void StoreProductInformation()
    {

//        loadingBar.setCanceledOnTouchOutside(false);
//        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
    //    String time=saveCurrentTime.toString();

        productRandomKey = saveCurrentDate + saveCurrentTime;


        final StorageReference filePath = ProductImagesRef.child(ImageUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(MainChat.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(MainChat.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

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
                            String chattitle=chat.getText().toString().trim();
                            ImageUploadInfo imageUploadInfo = new ImageUploadInfo(chattitle,task.getResult().toString());

                            // Getting image upload ID.
                            String ImageUploadId = ProductsRef.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            ProductsRef.child(ImageUploadId).setValue(imageUploadInfo);
                            databaseReference= FirebaseDatabase.getInstance().getReference().child(chattitle);
                            Message message=new Message("\n");
                            String id=databaseReference.push().getKey();
                           databaseReference.child(id).setValue(message);

                            Toast.makeText(MainChat.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }
        });

    }

private  void getdataf(){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("All_Image_Uploads_Database");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageUploadInfos.clear();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    ImageUploadInfo info=dataSnapshot1.getValue(ImageUploadInfo.class);
                    imageUploadInfos.add(info);
                }
                adapter=new ImgAdapter(MainChat.this,imageUploadInfos);
                rclist.setLayoutManager(new LinearLayoutManager(MainChat.this));
           rclist.smoothScrollToPosition(imageUploadInfos.size()-1);
           rclist.setAdapter(adapter);
                rclist.smoothScrollToPosition(imageUploadInfos.size()-1);




            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

}

    public void gui(View view) {
       String chatchit =chat.getText().toString().trim();
       Message message=new Message(chatchit);
       DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("message");
       String id=reference.push().getKey();
       reference.child(id).setValue(message);
    }
    public void nhan(){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("message");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Tomessage tomessage=dataSnapshot1.getValue(Tomessage.class);
                        messageList.add(tomessage);
                }
                messageAdapter=new messageAdapter(MainChat.this,messageList);
                rclist.setLayoutManager(new LinearLayoutManager(MainChat.this));
                rclist.setAdapter(messageAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



}