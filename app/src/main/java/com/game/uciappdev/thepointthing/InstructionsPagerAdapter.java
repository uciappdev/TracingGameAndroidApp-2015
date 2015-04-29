package com.game.uciappdev.thepointthing;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

public class InstructionsPagerAdapter extends FragmentStatePagerAdapter
{
    protected Context mContext;

    public InstructionsPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        mContext = context;
    }

    @Override
    //This method returns the fragment associated with
    //the specified position.
    //
    //It is called when the Adapter needs a fragment
    //and it does not exist
    public Fragment getItem(int position)
    {
        //Create fragmentObject
        Fragment fragment = new InstructionsFragment();

        //Attach some data to it that we'll
        //use to populate our fragment layouts
        Bundle args = new Bundle();

        //Our object is just an Integer
        args.putInt("page_position", position);

        //Set the arguments on the fragment
        //that will be fetched in DemoFragment@onCreateView
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    //getCount specifies how many views to show in the ViewPager and getItem generates the views
    //at the specified positions(passed in as an argument)
    public int getCount()
    {
        return 3;
    }
}
