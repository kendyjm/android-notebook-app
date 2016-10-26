package com.kendyjm.notebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NoteDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        // Grab and Hold reference of views so we can populate them with the specific note row data
        TextView noteTitle = (TextView) findViewById(R.id.noteTitle);
        TextView noteBody = (TextView) findViewById(R.id.noteBody);
        ImageView noteImg = (ImageView) findViewById(R.id.noteImg);

        // Fill each new referenced view with the data associated with note it's referencing
        noteTitle.setText(getIntent().getStringExtra(Note.Extras.TITLE));
        noteBody.setText(getIntent().getStringExtra(Note.Extras.MESSAGE));
        noteImg.setImageResource(getIntent().getIntExtra(Note.Extras.CATEGORY_ASSOCIATED_DRAWABLE, 0));
    }
}
