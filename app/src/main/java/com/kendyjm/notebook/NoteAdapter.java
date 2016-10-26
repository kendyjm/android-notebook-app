package com.kendyjm.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kendy on 22/10/16.
 */

public class NoteAdapter extends ArrayAdapter<Note> {
    /**
     * Constructor
     *
     * @param context The current context.
     * @param notes   The notes array
     */
    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    public static class ViewHolder {
        TextView noteTitle;
        TextView noteBody;
        ImageView noteImg;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the datat item for this position
        Note note = getItem(position);

        ViewHolder viewHolder;

        // Check if an existing view is being
        if (convertView == null) {

            // create a view holder to save our views references
            viewHolder = new ViewHolder();

            // ici on a la hierarchie de relation entre le layout 'list_row'->l'adapter (this)->MainActivityFragment->content_main.xml
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            // Grab and Hold reference of views so we can populate them with the specific note row data
            viewHolder.noteTitle = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
            viewHolder.noteBody = (TextView) convertView.findViewById(R.id.listItemNoteBody);
            viewHolder.noteImg = (ImageView) convertView.findViewById(R.id.listItemNoteImg);

            // rememeber out view holder holding our references to our widgets
            convertView.setTag(viewHolder);
        }
        else {
            // grab the views from existing view holder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Fill each new referenced view with the data associated with note it's referencing
        viewHolder.noteTitle.setText(note.getTitle());
        viewHolder.noteBody.setText(note.getMessage());
        viewHolder.noteImg.setImageResource(note.getAssociatedDrawable());

        return convertView;
    }
}
