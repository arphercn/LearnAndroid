package com.arpher.me;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.arpher.me.model.Category;
import com.arpher.me.model.ContactsManager;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ListView listview = (ListView) findViewById(R.id.categoryList);

        // retrieve data from the database
        ContactsManager cm        = new ContactsManager(this);
        List<Category> categories =  cm.getAllCategories();

        // transform data to a list of strings
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < categories.size(); ++i) {
            list.add(categories.get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        // bind the listview and the adapter
        listview.setAdapter(adapter);
    }
}
