package com.salma.application.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salma.application.helpers.DataBaseHelper;
import com.salma.application.models.Contact;
import com.salma.application.data.DataBase;
import com.salma.application.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent detail = getIntent();
        int position = detail.getIntExtra("position", -1);

        Contact contact = DataBase.ContactList.get(position);

        TextView name = findViewById(R.id.tvname);
        TextView phone = findViewById(R.id.tvphone);

        name.setText(contact.getName());
        phone.setText(contact.getPhone());


        Button backToList = findViewById(R.id.btnback);
        backToList.setOnClickListener(V -> {
            Intent intent_backTL = new Intent(DetailActivity.this, ContactListActivity.class);
            startActivity(intent_backTL);
        });

        Button deleteItem = findViewById(R.id.btndelete);
        deleteItem.setOnClickListener(v -> {
            if (position != -1) {
                Contact Contact = DataBase.ContactList.get(position);

                SQLiteDatabase db = DataBaseHelper.getInstance(this).getWritableDatabase();
                db.delete("contacts", "nom=? AND phone=?", new String[]{contact.getName(), contact.getPhone()});

                Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show();

                new AlertDialog.Builder(this).setTitle("delete contact")
                        .setMessage("are you sure to delete the contact")
                        .setPositiveButton("Delete", (dialog, which) -> {
                            DataBase.ContactList.remove(position);
                            Intent back = new Intent(this, ContactListActivity.class);
                            startActivity(back);
                            finish();
                        })
                        .setNegativeButton("Cancel", null)
                        .show();

            }


        });
    }}