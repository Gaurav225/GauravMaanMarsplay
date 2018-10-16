package com.example.gaurav.gauravmaanmarsplay;


import android.app.Application;
import android.content.Context;
import android.net.Uri;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.annotation.meta.When;


public class ViewModelMainTest {
    @Mock
    DataModelFirestore dataModel;

    @Mock
    Application application;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    Uri uri;

    private ViewModelMain iViewModel;

    @Before
    public void setUp() throws Exception {
        iViewModel = new ViewModelMain(application);
        iViewModel.dataModelFirestore = dataModel;
    }

    @Test
    public void shouldPass() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void checkIfMessageIsDisplaydIfNetworkNotConnected() {
        Mockito.when(dataModel.isNetworkAvilable(application)).thenReturn(false);

        iViewModel.uploadImage(uri);
        Mockito.verify(dataModel).postMessage("Network Unavailable:: Unable To Upload");

    }

    @Test
    public void checkIfUploadCalledIfNetworkConnected() {
        Mockito.when(dataModel.isNetworkAvilable(application)).thenReturn(true);

        iViewModel.uploadImage(uri);
        Mockito.verify(dataModel).uploadImage(uri);

    }
}