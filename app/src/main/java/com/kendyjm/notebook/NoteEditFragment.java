package com.kendyjm.notebook;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
    // why is it necessary ?
    private Note.Category savedButtonCategory;

    private AlertDialog categoryDialog;

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
        noteCatButton = (ImageButton) fragmentLayout.findViewById(R.id.editNoteButton);

        // grab the activity owning this fragment: fragment<-activity<-intent
        Intent intent = getActivity().getIntent();

        // Fill each new referenced view with the data associated with note it's referencing
        noteTitle.setText(intent.getStringExtra(Note.Extras.TITLE));
        noteBody.setText(intent.getStringExtra(Note.Extras.MESSAGE));
        noteCatButton.setImageResource(intent.getIntExtra(Note.Extras.CATEGORY_ASSOCIATED_DRAWABLE, 0));

        /* category dialog creation & process*/
        buildCategoryDialog();
        noteCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDialog.show();
            }
        });

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

}
