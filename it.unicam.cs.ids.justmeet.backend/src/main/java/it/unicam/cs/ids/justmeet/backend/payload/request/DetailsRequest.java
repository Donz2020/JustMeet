package it.unicam.cs.ids.justmeet.backend.payload.request;

public class DetailsRequest extends AuthRequest {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
