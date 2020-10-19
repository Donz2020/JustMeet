package it.unicam.cs.ids.justmeet.backend.payload.response;

import java.time.LocalDate;
import java.util.List;

public class PhysicalDetailsResponse extends DetailsResponse {

    String surname;

    LocalDate birthDate;

    public PhysicalDetailsResponse(String username, String details, List<String> roles,
                                   String name, String surname, LocalDate birthDate) {
        super(username, details, roles, name);
        this.surname = surname;
        this.birthDate = birthDate;
    }

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
