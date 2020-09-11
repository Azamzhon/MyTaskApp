package com.geektech.mytaskapp.profileFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.mytaskapp.R;
import com.geektech.mytaskapp.adapters.ProfileAdapter;
import com.geektech.mytaskapp.models.Box;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragmentPhotos extends Fragment {

    List<Box> boxList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_photos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boxList = new ArrayList<>();
        boxList.add(new Box("The Man","Category Box","Description Box",R.drawable.the_man));
        boxList.add(new Box("Hello","Category Box","Description Box",R.drawable.pic_hello));
        boxList.add(new Box("Let's Go","Category Box","Description Box",R.drawable.pic_lets_go));
        boxList.add(new Box("Working","Category Box","Description Box",R.drawable.pic_working));
        boxList.add(new Box("Road","Category Box","Description Box",R.drawable.ic_road));
        boxList.add(new Box("The Man","Category Box","Description Box",R.drawable.the_man));
        boxList.add(new Box("Hello","Category Box","Description Box",R.drawable.pic_hello));
        boxList.add(new Box("Let's Go","Category Box","Description Box",R.drawable.pic_lets_go));
        boxList.add(new Box("Working","Category Box","Description Box",R.drawable.pic_working));
        boxList.add(new Box("Road","Category Box","Description Box",R.drawable.ic_road));
        boxList.add(new Box("The Man","Category Box","Description Box",R.drawable.the_man));
        boxList.add(new Box("Hello","Category Box","Description Box",R.drawable.pic_hello));
        boxList.add(new Box("Let's Go","Category Box","Description Box",R.drawable.pic_lets_go));
        boxList.add(new Box("Working","Category Box","Description Box",R.drawable.pic_working));
        boxList.add(new Box("Road","Category Box","Description Box",R.drawable.ic_road));

        RecyclerView myRv = view.findViewById(R.id.prof_recycler);
        ProfileAdapter adapter = new ProfileAdapter( boxList);
        myRv.setLayoutManager(new GridLayoutManager(requireActivity(),3));
        myRv.setAdapter(adapter);
    }
}