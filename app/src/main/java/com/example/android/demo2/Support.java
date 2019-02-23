package com.example.android.demo2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        Button button= (Button) findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","abc@gmail.com", null));

                startActivity(emailIntent);

            }
        });

        Button button2= (Button) findViewById(R.id.imageButton2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String phone = "+966507526281";
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(call);

            }
        });

    }

    }

