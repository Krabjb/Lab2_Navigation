package ru.omgtu.lab2navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CallbackFragment extends BaseFragment {

    private CallbackListener callbackListener;

    public interface CallbackListener {
        void onButton1Clicked();
        void onButton2Clicked();
        void onButton3Clicked();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Проверяем, имплементит ли Activity наш интерфейс
        if (context instanceof CallbackListener) {
            callbackListener = (CallbackListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CallbackListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_callback, container, false);

        Button btn1 = view.findViewById(R.id.btn_callback_1);
        Button btn2 = view.findViewById(R.id.btn_callback_2);
        Button btn3 = view.findViewById(R.id.btn_callback_3);

        btn1.setOnClickListener(v -> callbackListener.onButton1Clicked());
        btn2.setOnClickListener(v -> callbackListener.onButton2Clicked());
        btn3.setOnClickListener(v -> callbackListener.onButton3Clicked());

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbackListener = null;
    }
}