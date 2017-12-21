package com.renanrhoden.eyeswap;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.samples.vision.face.photo.R;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

public class MainActivity extends Activity {

    public static final int PERMISSIONS_REQUEST_CODE = 0;
    public static final int FILE_PICKER_REQUEST_CODE = 1;

    private String firstFilePath = "";
    private String secondFilePath = "";
    private Bitmap firstPhoto;
    private Bitmap secondPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            firstFilePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            saveFirstPhoto();
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            secondFilePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            saveSecondPhoto();
        }
    }

    private void saveFirstPhoto() {
        firstPhoto = BitmapFactory.decodeFile(firstFilePath);
    }

    private void saveSecondPhoto() {
        secondPhoto = BitmapFactory.decodeFile(secondFilePath);
    }

    public void onFirstPhotoClick(View view) {
        checkPermissionsAndOpenFilePicker();
    }

    private void openFilePicker() {
        new MaterialFilePicker()
                .withActivity(this)
                .withRootPath("/storage/")
                .withRequestCode(1)
                .start();
    }

    public void onSecondPhotoClick(View view) {
        new MaterialFilePicker()
                .withActivity(this)
                .withRootPath("/storage/")
                .withRequestCode(2)
                .start();
    }

    public void onSwapEyesClick(View view) {
    }

    private void checkPermissionsAndOpenFilePicker() {
        String permission = Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showError();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSIONS_REQUEST_CODE);
            }
        } else {
            openFilePicker();
        }
    }

    private void showError() {
        Toast.makeText(this, "Allow external storage reading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openFilePicker();
                } else {
                    showError();
                }
            }
        }
    }


}
