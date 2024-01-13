package com.isamm.myspace;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import static com.isamm.myspace.MainMenu.player;


public class AccountFragment extends Fragment {

    //get player from shared preferences
    ActionBar actionBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_account, container, false);

    }

    @Override
    public void onViewCreated(View view,  @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setting up seekbar default level
        SeekBar playerLevel = view.findViewById(R.id.levelSeekBar);
        playerLevel.setProgress(0);
        playerLevel.incrementProgressBy(50);
        playerLevel.setMax(100);

        //getting info from current player
        TextView playerName = view.findViewById(R.id.player_name);
        playerName.setText(getResources().getText(R.string.name)+" "+player.getName()+" "+player.getLastName());

        TextView ageView = view.findViewById(R.id.player_age);
        ageView.setText(getResources().getText(R.string.age)+" "+String.valueOf(player.getAge()));

        TextView scoreView = view.findViewById(R.id.score_displayer);
        scoreView.setText(String.valueOf(player.getScore()));

        switch(player.getNiveau()){
            case 1 : playerLevel.setProgress(0);
                break;
            case 2 :  playerLevel.setProgress(50);
                break;
            case 3 :playerLevel.setProgress(100);
                break;
        }

        playerLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                i = i / 50;
                i = i * 50;
                playerLevel.setProgress(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int i = playerLevel.getProgress();
                Toast toast = Toast.makeText(getContext(), getResources().getText(R.string.easy),
                        Toast.LENGTH_SHORT);
                switch(i){
                    case 0 :
                        player.setNiveau(1);
                        toast.setText(getResources().getText(R.string.easy));
                        toast.show();
                        break;
                    case 50 :
                        player.setNiveau(2);
                        toast.setText(getResources().getText(R.string.medium));
                        toast.show();
                        break;
                    case 100 :
                        player.setNiveau(3);
                        toast.setText(getResources().getText(R.string.hard));
                        toast.show();
                        break;
                }
                player.updateSharedPref(getContext());
            }
        });

    }

}