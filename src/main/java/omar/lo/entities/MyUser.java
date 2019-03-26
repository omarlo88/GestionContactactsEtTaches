package omar.lo.entities;

import java.util.Collection;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class MyUser {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	@Column(unique = true,  nullable = false, length=50)
	private String username;
	private String password;
	@Email(message= "Il faut saisir un email valide!!")
	@Column(unique = true,  nullable = false)
	private String email;
	@ManyToMany(fetch=FetchType.EAGER)
	//private Collection<MyRole> roles = new ArrayList<>();
	private Collection<MyRole> roles = new TreeSet<>();
	
	public MyUser(String nom, String prenom, String username, String password, String email, Collection<MyRole> roles) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}


	public MyUser(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public MyUser() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Collection<MyRole> getRoles() {
		return roles;
	}


	public void setRoles(Collection<MyRole> roles) {
		this.roles = roles;
	}
	
}// MyUser
