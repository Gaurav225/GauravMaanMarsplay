package com.example.gaurav.gauravmaanmarsplay;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.google.firebase.firestore.Query;


public class ViewModelMain extends AndroidViewModel {

    DataModelFirestore dataModelFirestore;
    private Application application;


    public ViewModelMain(@NonNull Application application) {
        super(application);
        this.application = application;
        this.dataModelFirestore = new DataModelFirestore();

    }


    public boolean isNetworkAvailable() {
        return dataModelFirestore.isNetworkAvilable(application);

    }


    public MutableLiveData<Boolean> getIsUploading() {
        return dataModelFirestore.isUploading;
    }

    public MutableLiveData<Integer> getUploadProgress() {
        return dataModelFirestore.uploadProgress;
    }

    public void uploadImage(Uri imgUri) {
        if (dataModelFirestore.isNetworkAvilable(application))
            dataModelFirestore.uploadImage(imgUri);
        else
            dataModelFirestore.postMessage("Network Unavailable:: Unable To Upload");
    }

    public MutableLiveData<String> getMessage() {
        return dataModelFirestore.message;
    }

    public Query getImagesQuery() {
        return dataModelFirestore.getImagesQuery();
    }
}
