package com.salma.estonews.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.salma.estonews.R;
import com.salma.estonews.adapters.ArticleAdapter;
import com.salma.estonews.helpers.GsonHelper;
import com.salma.estonews.helpers.VolleyHelper;
import com.salma.estonews.models.Article;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData();
    }
    private void loadData() {
        String url = "https://jsonplaceholder.typicode.com/posts](https://jsonplaceholder.typicode.com/posts";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    List<Article> articles = GsonHelper.parseJson(response);
                    adapter = new ArticleAdapter(articles);
                    recyclerView.setAdapter(adapter);
        },
                error -> {
                    Toast.makeText(this, "Erreur réseau", Toast.LENGTH_SHORT).show();
                }
        );
        VolleyHelper.getInstance(this).addToRequestQueue(request);
    }
}
