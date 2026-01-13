package com.salma.application.ui;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salma.application.models.Contact;
import com.salma.application.data.DataBase;
import com.salma.application.helpers.DataBaseHelper;
import com.salma.application.R;

public class ContactListActivity extends AppCompatActivity {

    ArrayAdapter<Contact> contactAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listContact);
        DataBase.ContactList.clear();
        SQLiteDatabase db = DataBaseHelper.getInstance(this).getReadableDatabase();
        Cursor mycursor = db.rawQuery("SELECT nom, phone FROM contacts", null);

        if (mycursor != null) {
            try {
                while (mycursor.moveToNext()) {
                    String nom = mycursor.getString(mycursor.getColumnIndexOrThrow("nom"));
                    String phone = mycursor.getString(mycursor.getColumnIndexOrThrow("phone"));
                    DataBase.ContactList.add(new Contact(nom, phone));
                }
            } finally {
                mycursor.close();
            }
        }


        contactAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DataBase.ContactList);
        listView.setAdapter(contactAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent detail = new Intent(ContactListActivity.this, DetailActivity.class);
            detail.putExtra("position", position);
            startActivity(detail);
            Toast.makeText(this, "Clicked position: " + position, Toast.LENGTH_LONG).show();
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent_back = new Intent(ContactListActivity.this, ContactActivity.class);
            startActivity(intent_back);
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        DataBase.ContactList.clear();

        SQLiteDatabase db = DataBaseHelper.getInstance(this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nom, phone FROM contacts", null);

        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    String nom = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
                    String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                    DataBase.ContactList.add(new Contact(nom, phone));
                }
            } finally {
                cursor.close();
            }
        }

        contactAdapter.notifyDataSetChanged();


    }



}