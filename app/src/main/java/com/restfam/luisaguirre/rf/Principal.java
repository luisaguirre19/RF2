package com.restfam.luisaguirre.rf;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.HashMap;
import java.util.Map;

public class Principal extends AppCompatActivity {
    DatabaseReference ReferenceRoot;
    Button enviadatos;
    Button abrir;
    EditText nombre, apellido, nombreusual, telfono, mail;
    StorageReference mStorage;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_principal);



        enviadatos = findViewById(R.id.btnEnviar);
        abrir = findViewById(R.id.btnabrir);
        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        nombreusual = findViewById(R.id.txtNombreUsual);
        telfono = findViewById(R.id.txtTelefono);
        mail = findViewById(R.id.txtCorreo);

        ReferenceRoot = FirebaseDatabase.getInstance().getReference();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        ImageView targetImageView = (ImageView) findViewById(R.id.imageView);
        String internetUrl = "https://firebasestorage.googleapis.com/v0/b/rf-db-6f29b.appspot.com/o/a.jpg?alt=media&token=7cd37d8f-1de9-4f63-b100-a62ad2bf2104";

        Glide
                .with(Principal.this)
                .load(internetUrl)
                .into(targetImageView);


        ReferenceRoot.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ReferenceRoot.child("Usuario").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Escucha user = snapshot.getValue(Escucha.class);
                            String nombre = user.getNombre();
                            String apellido = user.getApellido();



                  /*              Toast toast1 = Toast.makeText(getApplicationContext(), "Nombre Usuario " + nombre, Toast.LENGTH_SHORT);
                                toast1.setGravity(Gravity.CENTER, 0, 0);
                                toast1.show();

                            Toast toast2 = Toast.makeText(getApplicationContext(), "Datos " + snapshot.getValue(), Toast.LENGTH_SHORT);
                            toast2.setGravity(Gravity.CENTER, 0, 0);
                            toast2.show();

*/


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        enviadatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mnombre = nombre.getText().toString();
                String mapellido = apellido.getText().toString();
                String mnombreusual = nombreusual.getText().toString();
                int mtelefono = Integer.parseInt(telfono.getText().toString());
                String mmail = mail.getText().toString();

                CargarUsuarioDB(mnombre, mapellido, mnombreusual, mtelefono, mmail);

            }
        });

        abrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), Login.class);
                startActivityForResult(intent, 0);
            }
        });

    }



    private void CargarUsuarioDB(String mnombre, String mapellido, String mnombreusual, int mtelefono, String mmail) {
        Map<String, Object> datosusuario = new HashMap<>();
        datosusuario.put("nombre", mnombre);
        datosusuario.put("apellido", mapellido);
        datosusuario.put("nombre_usual", mnombreusual);
        datosusuario.put("telefono", mtelefono);
        datosusuario.put("mail", mmail);
        ReferenceRoot.child("Usuario").push().setValue(datosusuario);
    }

}