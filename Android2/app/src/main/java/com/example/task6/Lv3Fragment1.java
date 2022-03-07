package com.example.task6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Lv3Fragment1 extends Fragment {
    ArrayList<Lv3Person> data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lv3_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.lv3_fragment1_rv);
        Lv3RecycleAdapter adapter = new Lv3RecycleAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    public void initData() {
        for (int i = 0; i < 30; i++) {
            Lv3Person person = new Lv3Person();
            person.setPerson1("廖老师是大卷怪");
            person.setPerson2("郭祥瑞是大卷怪");
            person.setPerson3("红岩学长都是大卷怪");
            data.add(person);
        }
    }
}
