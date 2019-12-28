package com.example.caredriving.ui.navigationBar.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caredriving.R;
import com.example.caredriving.ui.navigationBar.searchTeachers.RecyclerViewAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    private static ArrayList<String> dates = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private static RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        dates.add("Check 1");
        dates.add("Check 2");
        dates.add("Check 3");
        dates.add("Check 4");
        recyclerView = root.findViewById(R.id.recyclerviewAllLessons);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        RecyclerViewAdapterAllDates adapter = new RecyclerViewAdapterAllDates(root.getContext(), dates);
        recyclerView.setAdapter(adapter);

        return root;
    }
}