package com.example.caredriving.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.caredriving.R;

import java.util.ArrayList;


public class GalleryFragment extends Fragment {


    private static RecyclerView teachers;
    private static ArrayList<String> teachersNames = new ArrayList<>();

    public static GalleryFragment newInstance(){
        GalleryFragment galleryFragment = new GalleryFragment();
        return galleryFragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        teachersNames.add("a");
        teachersNames.add("b");
        teachersNames.add("c");
        teachersNames.add("d");
        RecyclerView recyclerView = root.findViewById(R.id.recyclerviewTeachers);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), teachersNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }

}
