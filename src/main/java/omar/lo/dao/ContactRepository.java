package omar.lo.dao;


import omar.lo.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findContactsByNomStartsWithOrderByNom(String nom);
    List<Contact> findContactsByPrenomStartingWithOrderByNom(String prenom);
    List<Contact> findContactsByDateNaissanceOrderByNom(Date date);
    Contact findContactsByEmail(String email);
    List<Contact> findContactsByNomAndPrenomAndDateNaissanceOrderByNom(String nom, String prenom, Date date);
}
