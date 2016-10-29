package com.kendyjm.notebook;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {


    public NoteEditFragment() {
        // Required empty public constructor
    }


    /**
     * @See NoteViewFragment, which is very similar
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        // Grab and Hold reference of views so we can populate them with the specific note row data
        EditText noteTitle = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        EditText noteBody = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        ImageButton noteImg = (ImageButton) fragmentLayout.findViewById(R.id.editNoteButton);

        // grab the activity owning this fragment: fragment<-activity<-intent
        Intent intent = getActivity().getIntent();

        // Fill each new referenced view with the data associated with note it's referencing
        noteTitle.setText(intent.getStringExtra(Note.Extras.TITLE));
        noteBody.setText(intent.getStringExtra(Note.Extras.MESSAGE));
        noteImg.setImageResource(intent.getIntExtra(Note.Extras.CATEGORY_ASSOCIATED_DRAWABLE, 0));

        // Inflate the layout for this fragment
        return fragmentLayout;
    }

}
