package com.kendyjm.notebook;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link ListFragment} subclass.
 */
public class MainActivityFragment extends ListFragment {

    private ArrayList<Note> notes;
    private NoteAdapter arrayAdapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /*String[] values = new String[] {
                "Android", "iPhone", "WindowsMobile"
        };
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);*/


        notes = new ArrayList<Note>();
        notes.add(new Note("title1", "message1", Note.Category.PERSONAL));
        notes.add(new Note("title2", "message2", Note.Category.TECHNICAL));
        notes.add(new Note("title3", "message3", Note.Category.FINANCE));
        notes.add(new Note("title4", "message4", Note.Category.QUOTE));
        notes.add(new Note("title1", "message1", Note.Category.PERSONAL));
        notes.add(new Note("title2", "message2", Note.Category.TECHNICAL));
        notes.add(new Note("title3", "message3", Note.Category.FINANCE));
        notes.add(new Note("title4", "message4", Note.Category.QUOTE));
        notes.add(new Note("title1", "message1", Note.Category.PERSONAL));
        notes.add(new Note("title2", "message2", Note.Category.TECHNICAL));
        notes.add(new Note("title3", "message3", Note.Category.FINANCE));
        notes.add(new Note("title4", "message4", Note.Category.QUOTE));
        notes.add(new Note("title1", "message1", Note.Category.PERSONAL));
        notes.add(new Note("title2", "message2", Note.Category.TECHNICAL));
        notes.add(new Note("title3", "message3", Note.Category.FINANCE));
        notes.add(new Note("title4", "message4", Note.Category.QUOTE));
        notes.add(new Note("title1", "message1", Note.Category.PERSONAL));
        notes.add(new Note("title2", "message2", Note.Category.TECHNICAL));
        notes.add(new Note("title3", "message3", Note.Category.FINANCE));
        notes.add(new Note("title4", "message4", Note.Category.QUOTE));
        notes.add(new Note("title1", "message1", Note.Category.PERSONAL));
        notes.add(new Note("title2", "message2", Note.Category.TECHNICAL));
        notes.add(new Note("title3", "message3", Note.Category.FINANCE));
        notes.add(new Note("title4", "message4", Note.Category.QUOTE));
        notes.add(new Note("title1", "message1", Note.Category.PERSONAL));
        notes.add(new Note("title2", "message2", Note.Category.TECHNICAL));
        notes.add(new Note("title3", "message3", Note.Category.FINANCE));
        notes.add(new Note("title4", "message4", Note.Category.QUOTE));
        notes.add(new Note("title1", "message1", Note.Category.PERSONAL));
        notes.add(new Note("title2", "message2", Note.Category.TECHNICAL));
        notes.add(new Note("title3", "message3", Note.Category.FINANCE));
        notes.add(new Note("title4", "message4", Note.Category.QUOTE));
        notes.add(new Note("title1", "message1", Note.Category.PERSONAL));
        notes.add(new Note("title2", "message2", Note.Category.TECHNICAL));
        notes.add(new Note("title3", "message3", Note.Category.FINANCE));
        notes.add(new Note("title4", "message4", Note.Category.QUOTE));


        arrayAdapter = new NoteAdapter(getActivity(),  notes);
        setListAdapter(arrayAdapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startNodeDetailsActivity(position);
    }

    private void startNodeDetailsActivity(int position) {
        Note note = (Note) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(), NoteDetailsActivity.class);
        intent.putExtra(Note.Extras.TITLE, note.getTitle());
        intent.putExtra(Note.Extras.MESSAGE, note.getMessage());
        intent.putExtra(Note.Extras.CATEGORY_ASSOCIATED_DRAWABLE, note.getAssociatedDrawable());

        startActivity(intent);
    }
}

