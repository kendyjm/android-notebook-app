package com.kendyjm.notebook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public enum FragmentToLaunch {
        EDIT(NoteEditFragment.class),
        VIEW(NoteViewFragment.class),
        CREATE(NoteEditFragment.class);

        public final static String EXTRA = MainActivity.class.getName() + ".FragmentToLaunch";

        private Class noteFragment;
        FragmentToLaunch(Class c) {
            noteFragment = c;
        }

        public Class getNoteFragment() {
            return noteFragment;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* TODO use FloatingActionButton for ADD NOTE button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        loadPreferences();
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
            Intent intent = new Intent(this, AppPreferences.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_add_note) {
            // go to the note detail activity...which should open the note edit fragment...
            Intent intent = new Intent(this, NoteDetailActivity.class);
            intent.putExtra(MainActivity.FragmentToLaunch.EXTRA, FragmentToLaunch.CREATE);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isBackgroundDark = sharedPreferences.getBoolean("background_color", false);
        Log.i("PREFERENCES", "isBackgroundDark:" + isBackgroundDark);
        if (isBackgroundDark) {
            LinearLayout mainLayout = (LinearLayout) findViewById(R.id.content_main);
            mainLayout.setBackgroundColor(getResources().getColor(R.color.darkPreference));
        }


        String notebookTitle = sharedPreferences.getString("title", getString(R.string.app_name));
        Log.i("PREFERENCES", "notebookTitle:" + notebookTitle);
        setTitle(notebookTitle);
    }
}
