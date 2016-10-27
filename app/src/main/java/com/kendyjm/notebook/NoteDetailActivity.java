package com.kendyjm.notebook;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        createAndAddFragment();
    }

    // @see Android App Development Tutorial - 54 - Dynamically Loading NoteViewFragment
    // we need to add (/remove! to switch to the edit mode) it dynamically ! that's why we do it programmatically
    // see the difference with content_main.xml where the fragment is just static!
    private void createAndAddFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // fragment to add
        NoteViewFragment noteViewFragment = new NoteViewFragment();
        setTitle(R.string.view_fragment_title);
        // see activity_note_detail.xml
        fragmentTransaction.add(R.id.note_container, noteViewFragment, "NOTE_VIEW_FRAGMENT");

        fragmentTransaction.commit();

    }
}
