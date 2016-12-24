package com.arpher.me.model;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Database Manager
 * Interfacing between the database and the object models
 * */

public class ContactsManager extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS   = "contacts";
    private static final String TABLE_CATEGORIES = "categories";

    // Contacts Table Columns names
    private static final String KEY_ID   = "id";
    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_URL  = "url";

    // Constructor
    public ContactsManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT UNIQUE" + ")";
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_CATEGORY_ID + " INTEGER," + KEY_URL + " TEXT,"
                + "FOREIGN KEY(" + KEY_CATEGORY_ID +") REFERENCES "
                + TABLE_CATEGORIES + "(" + KEY_ID + ")"
                + ")";
        db.execSQL(CREATE_CATEGORIES_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations for Contacts
     */

    public void createContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_URL, contact.getUrl());
        values.put(KEY_CATEGORY_ID, contact.getCategory().getId());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }


    // Getting single contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_URL, KEY_CATEGORY_ID }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Category category = getCategory(Integer.parseInt(cursor.getString(3)));
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                category);

        // return contact
        return contact;
    }

    // Getting all contacts
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new LinkedList<Contact>();

        // build the query
        String query = "SELECT  * FROM " + TABLE_CONTACTS;

        // get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // iterate over all retrieved rows
        Contact contact = null;
        if (cursor.moveToFirst()) {
            do {
                contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setUrl(cursor.getString(2));

                Category category = getCategory(Integer.parseInt(cursor.getString(3)));
                contact.setCategory(category);

                // Add category to categories
                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        // return books
        return contacts;
    }



    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_URL, contact.getUrl());
        values.put(KEY_CATEGORY_ID, contact.getCategory().getId());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations for Contacts
     */

    // Adding a single category
    public void createCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, category.getName());
        db.insert(TABLE_CATEGORIES, null, values);
        db.close();
    }

    // Getting single contact
    public Category getCategory(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORIES, new String[] { KEY_ID,
                        KEY_NAME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Category category = new Category(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));

        // return contact
        return category;
    }

    // Getting all categories
    public List<Category> getAllCategories() {
        List<Category> categories = new LinkedList<Category>();

        // build the query
        String query = "SELECT  * FROM " + TABLE_CATEGORIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // iterate over the categories
        Category category = null;
        if (cursor.moveToFirst()) {
            do {
                category = new Category();
                category.setId(Integer.parseInt(cursor.getString(0)));
                category.setName(cursor.getString(1));

                // Add category to categories
                categories.add(category);
            } while (cursor.moveToNext());
        }

        // return categories
        return categories;
    }

    // Updating single contact
    public int updateCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, category.getName());

        // updating row
        return db.update(TABLE_CATEGORIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(category.getId()) });
    }

    // Deleting single contact
    public void deleteCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORIES, KEY_ID + " = ?",
                new String[] { String.valueOf(category.getId()) });
        db.close();
    }
}
