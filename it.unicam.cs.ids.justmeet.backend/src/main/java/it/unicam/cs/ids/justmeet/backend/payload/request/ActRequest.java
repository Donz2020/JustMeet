package it.unicam.cs.ids.justmeet.backend.payload.request;

public class ActRequest extends UserRequest{

    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
