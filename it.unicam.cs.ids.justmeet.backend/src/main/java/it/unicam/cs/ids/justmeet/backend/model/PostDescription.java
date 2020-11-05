package it.unicam.cs.ids.justmeet.backend.model;

import it.unicam.cs.ids.justmeet.backend.model.enumeration.PostCategory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Document(collection = "postDescriptions")
public class PostDescription {

    @Transient
    public static final String SEQUENCE_NAME = "postDescriptions_sequence";

    @Id
    private long id;

    private PostCategory type;

    private boolean free;

    @NotNull
    @NotBlank
    private String text;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
