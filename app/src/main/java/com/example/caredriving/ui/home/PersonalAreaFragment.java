package com.example.caredriving.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.caredriving.R;

public class PersonalAreaFragment extends Fragment {

    private PersonalAreaViewModel personalAreaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        personalAreaViewModel =
                ViewModelProviders.of(this).get(PersonalAreaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personal_area, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        personalAreaViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}