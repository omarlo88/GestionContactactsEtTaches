package omar.lo.metier;

import omar.lo.entities.Contact;

import java.util.Date;
import java.util.List;

public interface IContact {

    Contact getContact(Long id);
    Contact addContact(Contact c);
    List<Contact> getContacts();
    void deleteContact(Long id);
    List<Contact> chercherContactByNom(String nom);
    List<Contact> chercherContactByPrenom(String prenom);
    Contact chercherContactByEmail(String email);
    List<Contact> chercherContactByDateNaiss(Date date);
    List<Contact> chercherContactByTousParam(String nom, String prenom, Date date);
}// IContact
