package com.example.gaurav.gauravmaanmarsplay;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.example.gaurav.gauravmaanmarsplay.databinding.ActivityMainBinding;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


public class ActivityMain extends AppCompatActivity {
    public static final String TAG = ActivityMain.class.getCanonicalName();
    ViewModelMain viewModelMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModelMain = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ViewModelMain.class);
        mainBinding.setViewmodel(viewModelMain);
        mainBinding.setLifecycleOwner(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new FragmentImageList())
                .commit();

        viewModelMain.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (s != null && !s.isEmpty())
                    Toast.makeText(ActivityMain.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void uploadImage(View view) {
        showImagePickerDialog();
    }

    private void showImagePickerDialog() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setRequestedSize(600, 600)  //--The Larger images will resize to the size
                .start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                viewModelMain.uploadImage(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}
