package it.unicam.cs.ids.justmeet.backend.model.enumeration;

public enum FeedbackType {

    REALLYBAD(1),
    BAD(2),
    DESCRETE(3),
    GOOD(4),
    REALLYGOOD(5);

    private int value;

    private FeedbackType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
