package com.kendyjm.notebook;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteViewFragment extends Fragment {


    public NoteViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_view, container, false);

        // Grab and Hold reference of views so we can populate them with the specific note row data
        TextView noteTitle = (TextView) fragmentLayout.findViewById(R.id.viewNoteTitle);
        TextView noteBody = (TextView) fragmentLayout.findViewById(R.id.viewNoteMessage);
        ImageView noteImg = (ImageView) fragmentLayout.findViewById(R.id.viewNoteIcon);

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
