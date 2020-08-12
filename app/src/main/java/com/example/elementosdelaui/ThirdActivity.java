package com.example.elementosdelaui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    private EditText etPhone, etWeb;
    private ImageButton ibCamera, ibWeb, ibCall;
    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etPhone = (EditText) findViewById(R.id.editTextPhone);
        etWeb = (EditText) findViewById(R.id.editTextTextWeb);
        ibCall = (ImageButton) findViewById(R.id.imageButtonCall);
        ibWeb = (ImageButton) findViewById(R.id.imageButtonWeb);
        ibCamera = (ImageButton) findViewById(R.id.imageButtonCamera);

        ibCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = etPhone.getText().toString();
                if (phoneNumber != null){
                    //comprobar version de android corriendo
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);

                    }else{
                        OlderVersion(phoneNumber);
                    }

                }
            }

            private void OlderVersion(String phoneNumber){
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
                if(CheckPermision(Manifest.permission.CALL_PHONE)){
                    startActivity(intentCall);
                }else{
                    Toast.makeText(ThirdActivity.this, "No es posible acceder a llamadas", Toast.LENGTH_SHORT).show();
                }

            }

        });



    }

    //metodo para verificar si el permiso fue aceptado en grantResults
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if(permission.equals(Manifest.permission.CALL_PHONE)){
                    //comprobar si el permiso fue aceptado o denegado
                    if(result == PackageManager.PERMISSION_GRANTED){
                        //concedio permiso
                        String phoneNumber = etPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phoneNumber));
                        startActivity(intentCall);
                    }else{
                        //denego permiso
                        Toast.makeText(ThirdActivity.this, "Permiso denegado", Toast.LENGTH_SHORT).show();

                    }

                }

                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }

    private boolean CheckPermision(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}