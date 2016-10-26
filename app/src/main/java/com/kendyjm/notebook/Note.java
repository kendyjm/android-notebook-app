package com.kendyjm.notebook;

import android.os.Bundle;

/**
 * POJO containing all requiered information about a Note
 * Created by kendy on 22/10/16.
 */

public class Note {
    private String title, message;
    private long noteId=0, dateCreatedMilli=0;
    private Category category;

    public enum Category {
        PERSONAL(R.drawable.p), TECHNICAL(R.drawable.t), QUOTE(R.drawable.q), FINANCE(R.drawable.f);

        private int drawable;
        Category(int drawable) {
            this.drawable = drawable;
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

    public Note(String title, String message, Category category) {
        this.title = title;
        this.message = message;
        this.category = category;
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
