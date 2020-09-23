package it.unicam.cs.ids.justmeet.backend.model.enumeration;

public enum PostCategory {

    SPORT("Sport"),
    STUDY("Study"),
    PARTY("Party"),
    FUN("Fun"),
    OTHER("Other");

    private String value;

    private PostCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
