package it.unicam.cs.ids.justmeet.backend.payload.request;

import java.time.LocalDate;

public class PhyDetailsRequest extends DetailsRequest{

    String surname;

    LocalDate birthDate;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
