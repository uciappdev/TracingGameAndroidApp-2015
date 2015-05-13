package com.game.uciappdev.thepointthing;

import android.content.Intent;
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
        Intent i = new Intent(this, GameStateActivity.class);
        Log.v("MainActivity", "Moving to Play State!");
        startActivity(i);
    }

    public void moveToInstState(View v){
        Intent i = new Intent(this, InstructionsActivity.class);
        Log.v("Main Activity", "Moving to Inst State");
        startActivity(i);
    }

    public void moveToOptionsState(View v){
        Intent i = new Intent(this, OptionsActivity.class);
        startActivity(i);
    }

    public void exitApp(View v){
        System.exit(0);
    }

}
