package com.example.elementosdelaui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnMain;
    private final String GREETER = "Austin baby";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMain = (Button) findViewById(R.id.button);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acceder a la setunda activity y mandarle un string
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // greeter es el nombre de la llave para mandar el bundle
                intent.putExtra("greeter", GREETER);
                startActivity(intent);
            }
        });
    }


}