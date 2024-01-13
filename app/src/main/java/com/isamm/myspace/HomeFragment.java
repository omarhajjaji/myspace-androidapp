package com.isamm.myspace;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements RecyclerViewClickListener {

private View view;
private RecyclerView recyclerView;
List<Planet> planets = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.planets);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fillList(planets);
        RecyclerAdapter adapter=new RecyclerAdapter(view.getContext(),planets,this);
        recyclerView.setAdapter(adapter);
    }

    private void fillList(List<Planet> planets) {
        planets.add(new Planet(getString(R.string.pluto),getString(R.string.pluto_desc),"black","pluto")) ;
        planets.add(new Planet(getString(R.string.earth),getString(R.string.earth_desc),"blue","earth")) ;
        planets.add(new Planet(getString(R.string.venus),getString(R.string.venus_desc),"green","venus")) ;
        planets.add(new Planet(getString(R.string.jupiter),getString(R.string.jupiter_desc),"orange","jupiter"));
        planets.add(new Planet(getString(R.string.mercury),getString(R.string.mercury_desc),"brown","mercury"));
        planets.add(new Planet(getString(R.string.mars),getString(R.string.mars_desc),"red","mars"));
        planets.add(new Planet(getString(R.string.saturn),getString(R.string.saturn_desc),"gray","saturn"));


    }

    @Override
    public void onClick(View view, Planet index) {
        Log.i("Item clicked: ",index.getName());
        Intent intent = new Intent(getActivity(),PlanetsActivity.class);
        intent.putExtra("planetName",index.getName());
        intent.putExtra("planetDescription",index.getDescription());
        intent.putExtra("planetColor",index.getColor());
        intent.putExtra("planetImgPath",index.getImgPath());
        startActivity(intent);
    }


}