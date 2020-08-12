package com.example.elementosdelaui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnGoThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.textView);
        btnGoThird = (Button) findViewById(R.id.btnGoThird);

        // tomar los datos del intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getString("greeter") != null) {
            // greeter es una llave para abrir el bundle
            String greeter = bundle.getString("greeter");
            Toast.makeText(SecondActivity.this, greeter, Toast.LENGTH_SHORT).show();
            textView.setText(greeter);
        } else {
            Toast.makeText(SecondActivity.this, "Vacío", Toast.LENGTH_SHORT).show();
        }

        btnGoThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}