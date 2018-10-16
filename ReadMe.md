
1. App Architecture Pattern Used : M-V-VM

2. Used Cloud Storage For Firebase for resource hosting

3. The big images are downsized before uploading them
  
4. Use 2 layout files for main activity for handling both landscape and portrait mode.
   In portrait mode the upload button appear at the top whereas in landscape the button appear at
   bottom right
   Also recyclerview has 2 items per row in portrait mode whereas in orientation mode 
   it has 3 items per row
   

   
 5. To lessen the boilerplate used Android Databinding 
 
 6. Used Livedata(Similar to Observable in rxJava) which is also lifecycle aware
 
 7. Used Espresso for UI testing and Mockito as mocking Library For unit testing
 
 
 Furthur Improvements:
   I have uploaded only a single image in database not along with their thumbnail because the thumbnail should be created 
   at the cloud not at th user device .
   
   You donot see check permission code inside my activity because permissions is handled by the image picker library which i am using
   
   