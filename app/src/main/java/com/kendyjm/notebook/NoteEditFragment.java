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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {

    private EditText noteTitle;
    private EditText noteMessage;
    private Note.Category noteCategory;
    private ImageButton noteCategoryButton;

    // dialogs
    private AlertDialog categoryDialog, saveNoteDialog;

    // save states
    private static final String MODIFIED_CATEGORY = NoteEditFragment.class.getName()+".Modified Category";

    public NoteEditFragment() {
        // Required empty public constructor
    }


    /**
     * Remember: this method is a executed every single time we change the orientation of our device...
     * so we need to save the edited category before orientation changing (before it comes from the intent, one time)
     * @see NoteEditFragment.onSaveInstanceState
     *
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
        Log.d("NoteEditFragment", "onCreateView start");

        if (savedInstanceState !=null) {

        }

        // Inflate the layout for this fragment
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        // Grab and Hold reference of views so we can populate them with the specific note row data
        noteTitle = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        noteMessage = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);
        noteCategoryButton = (ImageButton) fragmentLayout.findViewById(R.id.editNoteButton);
        Button saveButton = (Button) fragmentLayout.findViewById(R.id.saveNote);

        // grab the activity owning this fragment: fragment<-activity<-intent
        Intent intent = getActivity().getIntent();

        // Fill each new referenced view with the data associated with note it's referencing
        noteTitle.setText(intent.getExtras().getString(Note.Extras.TITLE, ""));
        noteMessage.setText(intent.getExtras().getString(Note.Extras.MESSAGE, ""));

        if (savedInstanceState == null) {
            // we come from our list fragment so just do everything normally
            noteCategory = (Note.Category) intent.getSerializableExtra(Note.Extras.CATEGORY);
            int ressourceId = intent.getIntExtra(Note.Extras.CATEGORY_ASSOCIATED_DRAWABLE, -1);
            if(ressourceId != -1) {
                noteCategoryButton.setImageResource(ressourceId);
            } // else P is the default image button

        }
        else {
            // if we grabed a category from our bundle then we know we changed orientation and saved information
            // so set our image button background to that category
            noteCategory = (Note.Category) savedInstanceState.get(MODIFIED_CATEGORY);
            noteCategoryButton.setImageResource(Note.categoryToDrawable(noteCategory));
        }

        /* category dialog creation & process */
        buildCategoryDialog();
        noteCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialog.show();
            }
        });

        /* save dialog creation & process */
        buildSaveNoteConfirmDialog();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNoteDialog.show();
            }
        });

        // Inflate the layout for this fragment
        return fragmentLayout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MODIFIED_CATEGORY, noteCategory);
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
                noteCategory = Note.Category.getCategoryFromLabel(selectedCategory);
                noteCategoryButton.setImageResource(noteCategory.getDrawable());
            }

        });

        categoryDialog = categoryBuilder.create();
    }


    private void buildSaveNoteConfirmDialog() {
        AlertDialog.Builder saveNoteConfirmBuilder = new AlertDialog.Builder(getActivity());
        saveNoteConfirmBuilder.setTitle(R.string.save_note_confirm_title);
        saveNoteConfirmBuilder.setMessage(R.string.save_note_confirm_message);


        saveNoteConfirmBuilder.setPositiveButton(R.string.save_note_confirm_button_OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("Save note", "Note title: " + noteTitle.getText() + ", message:" + noteMessage.getText() + ", category:" + noteCategory);

                // TODO save note process

                // then get back to the main activity
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        saveNoteConfirmBuilder.setNegativeButton(R.string.save_note_confirm_button_CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do noting here
            }
        });

        saveNoteDialog = saveNoteConfirmBuilder.create();
    }

}
