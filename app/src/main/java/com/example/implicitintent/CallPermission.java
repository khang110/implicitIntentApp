package com.example.implicitintent;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CallPermission extends AppCompatActivity {

    Button btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_permission);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.light_gray)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCall = (Button)findViewById(R.id.call_permission);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0868123123"));
                startActivity(intent);
            }
        });
    }
}