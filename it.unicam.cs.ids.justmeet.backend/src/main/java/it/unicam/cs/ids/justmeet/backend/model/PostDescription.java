package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.PostCategory;

public class PostDescription {

    private PostCategory type;

    private boolean free;

    private String text;


    public PostCategory getType() {
        return type;
    }

    public void setType(PostCategory type) {
        this.type = type;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
