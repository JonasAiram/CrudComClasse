package com.example.jonas.crudcomclasse;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jonas.crudcomclasse.view.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // Executar alguma regra de negocioes
        // Enquanto carrega a tela SPLASH

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        },SPLASH_TIME_OUT);

    }
}
