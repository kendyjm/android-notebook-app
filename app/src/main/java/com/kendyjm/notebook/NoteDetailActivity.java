package com.kendyjm.notebook;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteDetailActivity extends AppCompatActivity {

    public static final String NEW_NOTE_EXTRA = NoteDetailActivity.class.getName() + ".New Note";

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

        // grab intent and fragment to launch from our main activity list fragment
        Intent intent = getIntent();
        MainActivity.FragmentToLaunch fragToLaunch = (MainActivity.FragmentToLaunch) intent.getSerializableExtra(
                MainActivity.FragmentToLaunch.EXTRA
        );

        // grabbing our fragment manager and our fragment transaction so that we can add our edit or view fragment dynamically
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //should/could be done within the FragmentToLaunch Enum
        switch (fragToLaunch) {
            case EDIT:
                // create and add note edit fragment to note detail activity if that's what we want to launch
                NoteEditFragment noteEditFragment = new NoteEditFragment();
                setTitle(R.string.edit_fragment_title);

                // pass some arguments
                Bundle bundleEdit = new Bundle();
                bundleEdit.putBoolean(NEW_NOTE_EXTRA, false);
                noteEditFragment.setArguments(bundleEdit);

                // see activity_note_detail.xml
                fragmentTransaction.add(R.id.note_container, noteEditFragment, "NOTE_EDIT_FRAGMENT");
                break;

            case VIEW:
                // create and add note view fragment to note detail activity if that's what we want to launch
                NoteViewFragment noteViewFragment = new NoteViewFragment();
                setTitle(R.string.view_fragment_title);
                // see activity_note_detail.xml
                fragmentTransaction.add(R.id.note_container, noteViewFragment, "NOTE_VIEW_FRAGMENT");
                break;

            case CREATE:
                // create and add note edit fragment to note detail activity if that's what we want to launch
                NoteEditFragment noteCreateFragment = new NoteEditFragment();
                setTitle(R.string.create_fragment_title);

                // pass some arguments
                Bundle bundleCreate = new Bundle();
                bundleCreate.putBoolean(NEW_NOTE_EXTRA, true);
                noteCreateFragment.setArguments(bundleCreate);

                // see activity_note_detail.xml
                fragmentTransaction.add(R.id.note_container, noteCreateFragment, "NOTE_CREATE_FRAGMENT");
                break;
        }

        // commit our changes so that everything works
        fragmentTransaction.commit();
    }
}
