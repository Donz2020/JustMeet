package it.unicam.ids.justmeet;

/**
 * Questa interfaccia permette di identificare il ruolo
 * di un utente fisico.
 * @author Andrea
 *
 */
public interface IPhysicalUser extends IUser {

	UserRole GetRole();
}
