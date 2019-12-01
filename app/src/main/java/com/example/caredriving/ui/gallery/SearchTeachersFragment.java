package com.example.caredriving.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caredriving.R;

import java.util.ArrayList;


public class SearchTeachersFragment extends Fragment {


    private static RecyclerView teachers;
    private static ArrayList<String> teachersNames = new ArrayList<>();
    private static boolean added = false;

    public static SearchTeachersFragment newInstance(){
        SearchTeachersFragment searchTeachersFragment = new SearchTeachersFragment();
        return searchTeachersFragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_teachers, container, false);
        if(!added) {
            teachersNames.add("a");
            teachersNames.add("b");
            teachersNames.add("c");
            teachersNames.add("d");
            added = true;
        }
        RecyclerView recyclerView = root.findViewById(R.id.recyclerviewTeachers);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), teachersNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }

}
