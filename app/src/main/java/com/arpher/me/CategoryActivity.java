package com.arpher.me;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.arpher.me.model.Category;
import com.arpher.me.model.ContactsManager;
import android.widget.Toast;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ListView listview = (ListView) findViewById(R.id.categoryList);

        // retrieve data from the database
        ContactsManager cm        = new ContactsManager(this);
        final List<Category> categories =  cm.getAllCategories();

        CategoryAdapter adapter = new CategoryAdapter(this,
                R.layout.list_category, categories);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                // When clicked, show a Toast text
                Toast.makeText(getApplicationContext(),
                        "id:" + categories.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
