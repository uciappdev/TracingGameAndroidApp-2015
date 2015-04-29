package com.game.uciappdev.thepointthing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//Instances of this class are fragments representing a single
//object in our collection
public class InstructionsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //Inflate the layout resource that will be returned
        //The last two arguments ensure LayoutParams are inflated properly
        View rootView = inflater.inflate(R.layout.instructions_fragment, container, false);

        //Get the arguments that were supplied when
        //the fragment was instantiated in the
        //CustomPagerAdapter
        Bundle args = getArguments();
        switch(args.getInt("page_position"))
        {
            case 0:
            {
                ((TextView) rootView.findViewById(R.id.text)).setText("Page 1");
                ((ImageView)rootView.findViewById(R.id.imageView)).setImageResource(R.drawable.test1);
                break;
            }
            case 1:
            {
                ((TextView) rootView.findViewById(R.id.text)).setText("Page 2");
                ((ImageView)rootView.findViewById(R.id.imageView)).setImageResource(R.drawable.test2);
                break;
            }
            case 2:
            {
                ((TextView) rootView.findViewById(R.id.text)).setText("Page 3");
                ((ImageView)rootView.findViewById(R.id.imageView)).setImageResource(R.drawable.test3);
                break;
            }

        }
        //((TextView) rootView.findViewById(R.id.text)).setText("Page " + args.getInt("page_position"));
        return rootView;
    }
}
