package com.restfam.luisaguirre.rf;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.widget.Toast;

public class Login extends AppCompatActivity {

    //private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

         FirebaseStorage storage = FirebaseStorage.getInstance();
         StorageReference storageRef = storage.getReference();

         storageRef.child("splash/B.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               ImageView img = (ImageView) findViewById(R.id.imageView2);
                // Got the download URL for 'users/me/profile.png'
                Glide
                        .with(Login.this)
                        .load(uri)
                        .into(img);
                new Handler().postDelayed(new Runnable(){
                    public void run(){
                        // Cuando pasen los 3 segundos, pasamos a la actividad principal de la aplicación
                        Intent intent = new Intent(Login.this, menu_lateral.class);
                        startActivity(intent);
                        finish();
                    };
                }, 2000);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any
                Toast toast1 = Toast.makeText(getApplicationContext(), "error: " + exception, Toast.LENGTH_LONG);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
            }
        });








    }
}
