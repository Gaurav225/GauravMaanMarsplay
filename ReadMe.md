
1. App Architecture Pattern Used : M-V-VM

2. Used Cloud Storage For Firebase for resource hosting
  
4. Use 2 layout files for main activity for handling both landscape and portrait mode.
   In portrait mode the upload button appear at the top whereas in landscape the button appear at
   bottom right
   Also recyclerview has 2 items per row in portrait mode whereas in orientation mode 
   it has 3 items per row
   
 5. To lessen the boilerplate used Android Databinding 
 
 6. Used Livedata(Similar to Observable in rxJava) which is also lifecycle aware
 
 7. Used Roboelectric ,Mockito as mocking Library For testing