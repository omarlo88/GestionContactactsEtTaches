package omar.lo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Tache implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "nom_tache", unique = true,  nullable = false, length=50)
	//@Column(updatable = false, name = "flight_name", nullable = false, length=50)
	//@NotEmpty(message = "Il faut saisir le nom de la tâche")
	private String nom;
	@Lob
	private String description;
	@Column(name="date_creation")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	private Date dateCreation;
	@Column(name="date_expiration", nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/YYYY")
	private Date dateExpiration;
	@Type(type="true_false")
	private boolean statut;
	
	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Tache(String nom, String description, Date dateExpiration) {
		super();
		this.nom = nom;
		this.description = description;
		this.dateExpiration = dateExpiration;
		this.dateCreation = new Date();
		this.statut = true;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	

	public Date getDateExpiration() {
		return dateExpiration;
	}


	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}


	public boolean isStatut() {
		return statut;
	}



	public void setStatut(boolean statut) {
		this.statut = statut;
	}



	@Override
	public String toString() {
		return "Tache [id=" + id + ", nom=" + nom + ", description=" + description + ", dateCreation=" + dateCreation
				+ ", dateExpiration=" + dateExpiration + ", statut=" + statut + "]";
	}

	
}
