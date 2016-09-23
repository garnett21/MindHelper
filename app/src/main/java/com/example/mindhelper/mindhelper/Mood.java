package com.example.mindhelper.mindhelper;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by X550V-NB on 2016/7/7.
 */
public class Mood extends DialogFragment implements  View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.mood,null);

        setCancelable(false);
        return view;
    }

    @Override
    public  void onClick(View view){

    }

}
