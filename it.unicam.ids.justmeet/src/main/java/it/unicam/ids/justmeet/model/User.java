package it.unicam.ids.justmeet.model;

/**
 * Ogni istanza � un utente fisico
 * che verr� differenziato in base al ruolo
 *
 * @author Andrea
 */
public class User implements IPhysicalUser {

    String Email;

    String Name;

    String LastName;

    UserRole Role;

    public User(String Email, String Name, String LastName) {
        this.Email = Email;
        this.Name = Name;
        this.LastName = LastName;
    }

    @Override
    public String GetDetails() {
        return String.format("%s %s", Name, LastName);
    }

    @Override
    public String GetUniqueID() {
        return Email;
    }

    @Override
    public UserRole GetRole() {
        return Role;
    }

}
