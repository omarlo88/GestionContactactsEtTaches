package omar.lo.service;

public class MyUsreForm {
	
	private String nom;
	private String prenom;
	private String username;
	private String password;
	private String repassword;
	private String email;
	
	
	public MyUsreForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public MyUsreForm(String nom, String prenom, String username, String password, String repassword, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.repassword = repassword;
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



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
