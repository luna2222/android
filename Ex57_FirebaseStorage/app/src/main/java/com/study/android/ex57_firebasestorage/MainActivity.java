package com.study.android.ex57_firebasestorage;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  static  final  String TAG = "lecture";

    private  String[] permissions={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    private static final  int MULTPLE_PERMISSIONS= 101;

    private RadioGroup radioGroup;
    private ImageView imageView;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        initProgram();

    }
    private  boolean checkPermissions(){
        int result;
        List<String> permissionList= new ArrayList<>();
        for (String pm: permissions) {
            result= ContextCompat.checkSelfPermission(this,pm);
            if(result != PackageManager.PERMISSION_GRANTED){
                permissionList.add(pm);
            }
        }
        if(!permissionList.isEmpty()){
            ActivityCompat.requestPermissions(this,
                    permissionList.toArray(new String[permissionList.size()]),
                    MULTPLE_PERMISSIONS);
            return  false;
        }
        return true;
    }


    @Override
    public void  onRequestPermissionsResult(
            int requestCode, String permission[], int[] grantResults)
    {
        switch (requestCode){
            case  MULTPLE_PERMISSIONS: {
                if(grantResults.length>0){
                    for(int i=0; i<permission.length; i++){
                        if(permission[i].equals(this.permissions[0])){
                            if(grantResults[i] !=PackageManager.PERMISSION_GRANTED){
                                showNoPermissonToastAndFinish();
                            }
                        }else if(permission[i].equals(this.permissions[1])){
                            if(grantResults[i] !=PackageManager.PERMISSION_GRANTED){
                                showNoPermissonToastAndFinish();
                            }
                        }else if(permission[i].equals(this.permissions[2])){
                            if(grantResults[i] !=PackageManager.PERMISSION_GRANTED){
                                showNoPermissonToastAndFinish();
                            }
                        }
                    }
                }else {
                    showNoPermissonToastAndFinish();
                }
                return;
            }
        }
    }

    private  void  showNoPermissonToastAndFinish(){
        Toast.makeText(this, " 권한 요청에 동의 해주셔야 가능합니다.",
                Toast.LENGTH_SHORT).show();
        finish();

    }

    public void initProgram() {
        radioGroup = findViewById(R.id.radioGroup);
        imageView = findViewById(R.id.imageView);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int imageNum;
                switch (checkedId) {
                    default:
                    case R.id.radio0:
                        imageNum = 1;
                        break;
                    case R.id.radio1:
                        imageNum = 2;
                        break;
                    case R.id.radio2:
                        imageNum = 3;
                        break;
                    case R.id.radio3:
                        imageNum = 4;
                        break;
                }

                printImageList();
                downloadImage(imageNum);
            }
        });
    }

    public void downloadImage(int imageNum) {
        String folderName = "images";
        String imageName = String.format("Image%d.png", imageNum);

        // Storage 이미지 다운로드 경로
        String storagePath = imageName;

        StorageReference imageRef = mStorageRef.child(storagePath);

        try {
            // Storage 에서 다운받아 저장시킬 임시파일
            final File imageFile = File.createTempFile("Images", "png");
            imageRef.getFile(imageFile).addOnSuccessListener(
                    new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            // Success Case
                            Bitmap bitmapImage = BitmapFactory.decodeFile(imageFile.getPath());
                            imageView.setImageBitmap(bitmapImage);
                            Toast.makeText(getApplicationContext(), "Success !!", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Fail Case
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Fail !!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void  printImageList(){
         //별도의 메서드가 없다
        // 리얼타임 데이터 베이스에 저장하고 불러온는 방법이 있다
        }

        public  void  onButton1Clicked(View v){
            Intent intent= new Intent(getApplicationContext(),Main2Activity.class);
            startActivity(intent);
            finish();
        }
}


