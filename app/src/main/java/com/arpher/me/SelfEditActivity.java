package com.arpher.me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SelfEditActivity extends AppCompatActivity {
    private EditText nameInput;
    private EditText urlInput;
    private TextView tvSubmit;

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_edit);

        // find views
        tvSubmit   = (TextView) findViewById(R.id.submit);
        nameInput  = (EditText) findViewById(R.id.name);
        urlInput   = (EditText) findViewById(R.id.url);

        sharedPref = this.getSharedPreferences("com.arpher.me",
                Context.MODE_PRIVATE);

        // submit
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String url  = urlInput.getText().toString();

                // Save to Shared Preferences
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("name", name);
                editor.putString("url", url);
                editor.commit();

                // End Current Activity
                SelfEditActivity.this.finish();

            }
        });
    }
}
