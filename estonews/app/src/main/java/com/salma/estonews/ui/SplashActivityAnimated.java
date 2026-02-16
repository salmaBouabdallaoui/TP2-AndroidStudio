package com.salma.estonews.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salma.estonews.R;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.salma.estonews.R;

public class SplashActivityAnimated extends AppCompatActivity {
    private static final int SPLASH_DURATION = 3000; // 3 secondes
    private static final int ANIMATION_DURATION = 1000; // 1 seconde

    private ImageView imgLogo;
    private TextView txtAppName;
    private TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Masquer la barre d'action si elle existe
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialiser les vues
        imgLogo = findViewById(R.id.imgLogo);
        txtAppName = findViewById(R.id.txtAppName);
        txtSlogan = findViewById(R.id.txtSlogan);

        // Lancer les animations
        startAnimations();

        // Rediriger vers HomeActivity après le délai
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivityAnimated.this, HomeActivity.class);
            startActivity(intent);
            finish();
            // Ajouter une transition fluide
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }, SPLASH_DURATION);
    }

    private void startAnimations() {
        // Animation du logo : fade in + scale
        imgLogo.setAlpha(0f);
        imgLogo.setScaleX(0.3f);
        imgLogo.setScaleY(0.3f);

        imgLogo.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();

        // Animation du nom de l'app : fade in + slide from bottom
        txtAppName.setAlpha(0f);
        txtAppName.setTranslationY(50f);

        txtAppName.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(ANIMATION_DURATION)
                .setStartDelay(300)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();

        // Animation du slogan : fade in
        txtSlogan.setAlpha(0f);

        txtSlogan.animate()
                .alpha(1f)
                .setDuration(ANIMATION_DURATION)
                .setStartDelay(600)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }
}