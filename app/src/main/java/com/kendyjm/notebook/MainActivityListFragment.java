package com.kendyjm.notebook;


import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link ListFragment} subclass.
 */
public class MainActivityListFragment extends ListFragment {

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
        notes.add(new Note("title111111111111111111111111111111111111111111111111111111111", "message11111111111111111111111111111111111111111111111111111111111111111111111", Note.Category.PERSONAL));
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

        // register the contet menu on a specific view
        registerForContextMenu(getListView());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        launchNoteDetailActivity(MainActivity.FragmentToLaunch.VIEW, position);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.long_press_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item ){
        // retrieve the position of the note from the listfragment
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int rowPosition = info.position;

        // id of the item from the menu
        switch (item.getItemId()) {
            case R.id.edit:
                launchNoteDetailActivity(MainActivity.FragmentToLaunch.EDIT, rowPosition);
                Log.d("Menu clicks", "we pressed edit");
                return true;
        }

        return super.onContextItemSelected(item);
    }

    private void launchNoteDetailActivity(MainActivity.FragmentToLaunch fragToLaunch, int position) {
        Note note = (Note) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(), NoteDetailActivity.class);
        intent.putExtra(Note.Extras.TITLE, note.getTitle());
        intent.putExtra(Note.Extras.MESSAGE, note.getMessage());
        intent.putExtra(Note.Extras.CATEGORY_ASSOCIATED_DRAWABLE, note.getAssociatedDrawable());
        intent.putExtra(Note.Extras.ID, note.getId());
        intent.putExtra(MainActivity.FragmentToLaunch.EXTRA, fragToLaunch);

        startActivity(intent);
    }
}

