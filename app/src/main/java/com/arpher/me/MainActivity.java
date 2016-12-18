package com.arpher.me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // attach view tree to the activity
        setContentView(R.layout.activity_main);

        // monitor "click" action for the button
        Button btn = (Button) findViewById(R.id.author);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        getString(R.string.author),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
