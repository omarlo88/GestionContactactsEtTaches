package omar.lo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyRole implements Comparable<MyRole>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true,  nullable = false, length=50)
	private String roleName;
	
	public MyRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyRole(String roleName) {
		super();
		this.roleName = roleName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		return ((MyRole)obj).id == id;
	}
	@Override
	public int compareTo(MyRole role) {
		// TODO Auto-generated method stub
		//int res = roleName.compareTo(role.roleName);
		return roleName.compareTo(role.roleName);
	}
	
	
}// MyRole
