package com.arpher.me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.arpher.me.model.Contact;
import com.arpher.me.model.Category;
import com.arpher.me.model.ContactsManager;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // attach view tree to the activity
        setContentView(R.layout.activity_main);

        sharedPref = this.getSharedPreferences("com.arpher.me",
                Context.MODE_PRIVATE);

        // monitor "click" action for the button
        Button btn = (Button) findViewById(R.id.author);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,
//                        getString(R.string.author),
//                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SelfEditActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


        ContactsManager cm = new ContactsManager(this);

        // add categories to the database
        cm.createCategory(new Category("Friend"));
        cm.createCategory(new Category("Enemy"));

        // add contact to the database
        Category cat1 = cm.getCategory(1);
        cm.createContact(new Contact("arpher", "http://arpher.com/about", cat1));

        // retrieve and display
        Toast.makeText(this, cm.getContact(1).getName(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView nameView = (TextView) findViewById(R.id.welcome);

        // retrieve content from shared preference, with key "name"
        String   welcome  = "Welcome, " + sharedPref.getString("name", "unknown") + "!";
        nameView.setText(welcome);
    }
}
