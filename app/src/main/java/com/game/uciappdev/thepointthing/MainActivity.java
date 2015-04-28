package com.game.uciappdev.thepointthing;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Click handlers for buttons on main menu screen

    public void moveToPlayState(View v){
        Log.v("MainActivity", "Moving to Play State!");
    }

    public void moveToInstState(View v){
        Log.v("Main Activity", "Moving to Inst State");
    }

    public void moveToOptionsState(View v){
        Log.v("Main Activity", "Moving to Option State");
    }

    public void exitApp(View v){
        System.exit(0);
    }

}
