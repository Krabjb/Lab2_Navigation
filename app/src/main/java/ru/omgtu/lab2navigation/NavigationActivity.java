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

    // Задание 3: Фрагменты и бэкстек
    // Бизнес-задача: приложение-опросник с последовательными шагами.
    // Пользователь проходит шаги: Вопрос 1 -> Вопрос 2 -> Результат.
    // Кнопка "Назад" возвращает на предыдущий шаг.

    private void loadFragmentWithBackStack(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    // Задание 2:
    // Бизнес-задача: приложение с логаутом.
    // После выхода из аккаунта пользователь не должен возвращаться
    // на предыдущий экран при нажатии "Назад".

    private void logoutAndStartNewActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    // Задание 3: Метод для открытия фрагментов с добавлением в бэкстек
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

    // Задание 2: Открытие новой Activity с флагами очистки стека
    public void openNewActivityWithClearFlag() {
        showToast("Logging out... Clearing activity stack");
        logoutAndStartNewActivity();
    }

    // Реализация коллбэков из CallbackFragment (Задание 1)
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
public void onLogoutClick(View view) {
    openNewActivityWithClearFlag();
}