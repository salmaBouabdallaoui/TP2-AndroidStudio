package com.salma.application.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salma.application.R;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Intent intent1 =getIntent();
        String username = intent1.getStringExtra("USER");
        TextView WelcomeTV = findViewById(R.id.welcome);
        WelcomeTV.setText(String.format("welcome  %s", username));

        Button logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener( v -> {
            Intent intent_logout = new Intent(ContactActivity.this, MainActivity.class);
            startActivity(intent_logout);
            Toast.makeText(this,"logout successfully", Toast.LENGTH_LONG).show();
            finish();

        });
        Button display = findViewById(R.id.btnDisplay);

        display.setOnClickListener(v -> {

            Intent intent_display = new Intent(ContactActivity.this, ContactListActivity.class);
            startActivity(intent_display);
        });

        Button add = findViewById(R.id.btnAdd);
        add.setOnClickListener(v -> {
            Intent intent_add = new Intent(ContactActivity.this, ContactFormActivity.class);
            intent_add.putExtra("username", getIntent().getStringExtra("username"));
            startActivity(intent_add);
        });

    }
}