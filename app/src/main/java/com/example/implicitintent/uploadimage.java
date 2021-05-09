package com.example.implicitintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class uploadimage extends AppCompatActivity {


    ImageButton mChooseBtn;
    //Khai bao cac phan tu imageview de chua cac hinh anh muon upload
    ImageView imgView1, imgView2, imgView3, imgView4, imgView5;
    ImageView[] listImage = new ImageView[5];
    int count;//Bien de kiem tra co bao nhieu buc anh da duoc chon

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimage);

        //        Add back button at toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mChooseBtn = findViewById(R.id.choose_image_btn);
        imgView1 = findViewById(R.id.image_view1);
        imgView2 = findViewById(R.id.image_view2);
        imgView3 = findViewById(R.id.image_view3);
        imgView4 = findViewById(R.id.image_view4);
        imgView5 = findViewById(R.id.image_view5);
        listImage[0] = imgView1;
        listImage[1] = imgView2;
        listImage[2] = imgView3;
        listImage[3] = imgView4;
        listImage[4] = imgView5;
        count = 0;


        mChooseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }else{
                        pickImageFromGallery();
                    }
                }else{

                }
            }
        });
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch(requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }else{
                    Toast.makeText( this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            listImage[count].setImageURI(data.getData());
            count = count + 1;
        }
    }
}