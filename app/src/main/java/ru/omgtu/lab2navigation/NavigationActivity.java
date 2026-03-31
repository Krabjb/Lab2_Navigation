package ru.omgtu.lab2navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NavigationActivity extends BaseActivity
        implements CallbackFragment.CallbackListener {

    private static final String TAG_FRAGMENT_A = "fragment_a";
    private static final String TAG_FRAGMENT_B = "fragment_b";
    private static final String TAG_FRAGMENT_C = "fragment_c";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        if (savedInstanceState == null) {
            loadFragment(new CallbackFragment(), false);
        }

        setupBackButton();
    }

    private void setupBackButton() {
        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    private void loadFragmentWithBackStack(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    private void logoutAndStartNewActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void openFragmentWithBackStack(String fragmentType) {
        Fragment fragment;

        switch (fragmentType) {
            case "A":
                fragment = new FragmentA();
                break;
            case "B":
                fragment = new FragmentB();
                break;
            default:
                fragment = new FragmentC();
                break;
        }

        loadFragmentWithBackStack(fragment, fragmentType);
        showToast("Opened Fragment " + fragmentType + " (added to back stack)");
    }

    public void openNewActivityWithClearFlag() {
        showToast("Logging out... Clearing activity stack");
        logoutAndStartNewActivity();
    }


    public void onLogoutClick(View view) {
        openNewActivityWithClearFlag();
    }

    // Реализация коллбэков
    @Override
    public void onButton1Clicked() {
        showToast("Button 1 clicked - Opening Fragment A via callback");
        openFragmentWithBackStack("A");
    }

    @Override
    public void onButton2Clicked() {
        showToast("Button 2 clicked - Opening Fragment B via callback");
        openFragmentWithBackStack("B");
    }

    @Override
    public void onButton3Clicked() {
        showToast("Button 3 clicked - Opening Fragment C via callback");
        openFragmentWithBackStack("C");
    }
}