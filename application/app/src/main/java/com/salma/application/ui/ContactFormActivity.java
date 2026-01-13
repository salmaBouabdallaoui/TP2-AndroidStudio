package com.salma.application.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salma.application.models.Contact;
import com.salma.application.data.DataBase;
import com.salma.application.helpers.DataBaseHelper;
import com.salma.application.R;

public class ContactFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText name = findViewById(R.id.name);
        EditText phone = findViewById(R.id.phone);


        Button submit = findViewById(R.id.btnsubmit);
        submit.setOnClickListener( v ->{
            String nom = name.getText().toString().trim();
            String phoneNbr = phone.getText().toString().trim();

            Contact newContact = new Contact(nom, phoneNbr);
            DataBase.ContactList.add(newContact);

            SQLiteDatabase sqLiteDatabase = DataBaseHelper.getInstance(this).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nom", nom);
            values.put("phone", phoneNbr);
            sqLiteDatabase.insert("contacts", null, values);


            Toast.makeText(this,"added",Toast.LENGTH_LONG).show();
            Intent intent_submit = new  Intent(ContactFormActivity.this, ContactActivity.class);
            startActivity(intent_submit);

            });



    }
}