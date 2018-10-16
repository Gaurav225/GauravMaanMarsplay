package com.example.gaurav.gauravmaanmarsplay;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DataModelFirestore {

    public MutableLiveData<Integer> uploadProgress;
    public MutableLiveData<Boolean> isUploading;
    MutableLiveData<String> message;

    public DataModelFirestore() {
        uploadProgress = new MutableLiveData<>();
        uploadProgress.postValue(0);
        isUploading = new MutableLiveData<>();
        message = new MutableLiveData<>();
    }

    public void uploadImage(Uri imgUri) {
        String img_name = "img_" + System.currentTimeMillis() + ".jpg";
        final StorageReference imagesRef = FirebaseStorage.getInstance().getReference("images/" + img_name);
        final FirebaseFirestore database = FirebaseFirestore.getInstance();
        isUploading.postValue(true);
        imagesRef.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imgUrl = "https://firebasestorage.googleapis.com" + uri.getEncodedPath() + "?alt=media";
                        database.collection("images").add(new ImageMetaData(imgUrl));
                    }
                });
                uploadProgress.postValue(0);
                isUploading.postValue(false);

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                        .getTotalByteCount());
                uploadProgress.postValue((int) progress);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                uploadProgress.postValue(0);
                isUploading.postValue(false);
            }
        });

    }

    public Query getImagesQuery() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        return database.collection("images").orderBy("url", Query.Direction.DESCENDING);
    }


    public boolean isNetworkAvilable(Application application) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null)
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void postMessage(String s) {
        message.postValue(s);
    }
}
