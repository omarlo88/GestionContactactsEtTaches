package omar.lo.metier;

import omar.lo.dao.ContactRepository;
import omar.lo.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContactImpl implements IContact {

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public Contact addContact(Contact c) {
        return contactRepository.save(c);
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContact(Long id) {
        return contactRepository.getOne(id);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.delete(id);
    }

    @Override
    public List<Contact> chercherContactByNom(String nom) {
        return contactRepository.findContactsByNomStartsWithOrderByNom(nom);
    }

    @Override
    public List<Contact> chercherContactByPrenom(String prenom) {
        return contactRepository.findContactsByPrenomStartingWithOrderByNom(prenom);
    }

    @Override
    public Contact chercherContactByEmail(String email) {
        return contactRepository.findContactsByEmail(email);
    }

    @Override
    public List<Contact> chercherContactByDateNaiss(Date date) {
        return contactRepository.findContactsByDateNaissanceOrderByNom(date);
    }

    @Override
    public List<Contact> chercherContactByTousParam(String nom, String prenom, Date date) {
        return contactRepository.findContactsByNomAndPrenomAndDateNaissanceOrderByNom(nom, prenom, date);
    }
}// Contactimpl
