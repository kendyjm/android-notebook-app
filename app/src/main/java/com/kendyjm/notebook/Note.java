package com.kendyjm.notebook;

import android.os.Bundle;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * POJO containing all requiered information about a Note
 * Created by kendy on 22/10/16.
 */

public class Note {
    private String title, message;
    private long noteId=0, dateCreatedMilli=0;
    private Category category;

    public enum Category {
        PERSONAL("Personal", R.drawable.p),
        TECHNICAL("Technical", R.drawable.t),
        QUOTE("Quote", R.drawable.q),
        FINANCE("Finance", R.drawable.f);

        private String label;
        private int drawable;

        private static class Holder {
            static Map<String, Category> MAP = new LinkedHashMap<String, Category>();

        }

        Category(String label, int drawable) {
            this.label = label;
            this.drawable = drawable;
            Holder.MAP.put(label, this);
        }

        public String getLabel() {
            return label;
        }

        public static Set<String> getCategoriesSet() {
            return Holder.MAP.keySet();
        }

        public static String[] getCategoriesArray() {
            return Holder.MAP.keySet().toArray(new String[Holder.MAP.keySet().size()]);
        }

        public static int getItemPosition(Category category) {
            int checkedItem = 0;
            String[] categories = getCategoriesArray();
            for (int i = 0; i < categories.length; i++) {
                if(categories[i].equals(category.getLabel())) {
                    checkedItem = i;
                    break;
                }
            }

            return checkedItem;
        }

        public static Category getCategoryFromLabel(String label) {
            return Holder.MAP.get(label);
        }

        public int getDrawable() {
            return drawable;
        }
    };

    public static class Extras {
        public final static String TITLE = Note.class.getName() + ".TITLE";
        public final static String MESSAGE = Note.class.getName() + ".MESSAGE";
        public final static String ID = Note.class.getName() + ".ID";
        public final static String CREATION_DATE = Note.class.getName() + ".CREATION_DATE";
        public final static String CATEGORY = Note.class.getName() + ".CATEGORY";
        public final static String CATEGORY_ASSOCIATED_DRAWABLE = Note.class.getName() + ".CATEGORY_ASSOCIATED_DRAWABLE";
    }

    /*public Note(String title, String message, Category category) {
        this(System.currentTimeMillis(), title, message, category, System.currentTimeMillis());
    }*/

    public Note(long noteId, String title, String message, Category category, long dateCreatedMilli) {
        this.title = title;
        this.message = message;
        this.category = category;
        this.noteId = noteId;
        this.dateCreatedMilli = dateCreatedMilli;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public Category getCategory() {
        return category;
    }

    public long getDate() {
        return dateCreatedMilli;
    }

    public long getId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", noteId=" + noteId +
                ", dateCreatedMilli=" + dateCreatedMilli +
                ", category=" + category +
                '}';
    }

    public int getAssociatedDrawable(){
        return categoryToDrawable(category);
    }

    public static int categoryToDrawable(Category noteCategory) {
        return noteCategory.getDrawable();
    }

}
