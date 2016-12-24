package com.arpher.me;

import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.arpher.me.model.Contact;
import com.arpher.me.model.ContactsManager;

public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        // Get extra data from the Intent
        // i.e., category id
        Intent intent = getIntent();
        int cat_id    = intent.getIntExtra("CATEGORY_ID", -1);

        ContactsManager cm     = new ContactsManager(this);
        final List<Contact> contacts = cm.getContactsByCategoryId(cat_id);

        ListView listview = (ListView) findViewById(R.id.contactList);

        ContactAdapter adapter = new ContactAdapter(this,
                R.layout.list_contact, contacts);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent intent = new Intent(ContactListActivity.this, BlogActivity.class);
                // Put extra data into the Intent, which is a URL
                intent.putExtra("BLOG_URL", contacts.get(position).getUrl());

                startActivity(intent);
            }
        });
    }
}
