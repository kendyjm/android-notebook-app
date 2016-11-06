package com.kendyjm.notebook;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {

    private ImageButton noteCatButton;
    private EditText noteTitle;
    private EditText noteBody;
    private Note.Category savedButtonCategory;

    // dialogs
    private AlertDialog categoryDialog, saveNoteDialog;

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
        noteTitle = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        noteBody = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        noteCatButton = (ImageButton) fragmentLayout.findViewById(R.id.editNoteButton);

        // grab the activity owning this fragment: fragment<-activity<-intent
        Intent intent = getActivity().getIntent();

        // Fill each new referenced view with the data associated with note it's referencing
        noteTitle.setText(intent.getStringExtra(Note.Extras.TITLE));
        noteBody.setText(intent.getStringExtra(Note.Extras.MESSAGE));
        noteCatButton.setImageResource(intent.getIntExtra(Note.Extras.CATEGORY_ASSOCIATED_DRAWABLE, 0));

        /* category dialog creation & process */
        buildCategoryDialog();
        noteCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialog.show();
            }
        });

        /* save dialog creation & process */
        buildSaveNoteConfirmDialog();

        // Inflate the layout for this fragment
        return fragmentLayout;
    }

    /**
     * Build category dialog to select/edit a category
     */
    private void buildCategoryDialog() {
        final String[] categories = Note.Category.getCategoriesArray();
        AlertDialog.Builder categoryBuilder = new AlertDialog.Builder(getActivity());
        categoryBuilder.setTitle(R.string.choose_note_category);

        // TODO find the right checkedItem
        categoryBuilder.setSingleChoiceItems(categories, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                // dismisses our dialog window
                categoryDialog.cancel();

                String selectedCategory = categories[item];
                savedButtonCategory = Note.Category.getCategoryFromLabel(selectedCategory);
                noteCatButton.setImageResource(savedButtonCategory.getDrawable());
            }

        });

        categoryDialog = categoryBuilder.create();
    }


    private void buildSaveNoteConfirmDialog() {
        AlertDialog.Builder saveNoteConfirmBuilder = new AlertDialog.Builder(getActivity());
        saveNoteConfirmBuilder.setTitle(R.string.save_note_confirm_title);
        saveNoteConfirmBuilder.setMessage(R.string.save_note_confirm_message);


        saveNoteConfirmBuilder.setPositiveButton(R.string.save_note_confirm_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Save note", "Note title: " + noteTitle.getText() + ", message:" + noteBody.getText() + ", category:" + noteCatButton);

                // save process

                // then get back to the main activity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        saveNoteConfirmBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do noting here
            }
        });

        saveNoteDialog = saveNoteConfirmBuilder.create();
    }

}
