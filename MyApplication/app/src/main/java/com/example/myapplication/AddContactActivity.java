package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPhone;
    private Button buttonSave;
    private Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String phone = editTextPhone.getText().toString();

                if (name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(AddContactActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Contact contact = new Contact(name, phone);
                    ContactManager.getInstance().addContact(contact);
                    Toast.makeText(AddContactActivity.this, "Contact added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}