package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DisplayListActivity<Contact> extends AppCompatActivity {
    private ListView listViewContacts;
    private Button buttonBack;
    private ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        listViewContacts = findViewById(R.id.listViewContacts);
        buttonBack = findViewById(R.id.buttonBack);

        ArrayList<Contact> contacts = (ArrayList<Contact>) ContactManager.getInstance().getContacts();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        listViewContacts.setAdapter(adapter);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}