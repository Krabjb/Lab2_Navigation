package ru.omgtu.lab2navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNavigation = findViewById(R.id.btn_go_to_navigation);
        btnNavigation.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
            startActivity(intent);
        });
    }
}