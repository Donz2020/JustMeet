package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.FeedbackType;

public class Feedback {

    private int userHash;

    private int postHash;

    private FeedbackType type;

    private String text;

    public boolean addAdditionalText(String text){
        if (text.length()>5) {
            this.text = text;
            return true;
        }else
            return false;
    }

    public int getUserHash() {
        return userHash;
    }

    public void setUserHash(int userHash) {
        this.userHash = userHash;
    }

    public int getPostHash() {
        return postHash;
    }

    public void setPostHash(int postHash) {
        this.postHash = postHash;
    }

    public FeedbackType getType() {
        return type;
    }

    public void setType(FeedbackType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }
}
