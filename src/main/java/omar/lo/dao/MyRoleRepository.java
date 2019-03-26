package omar.lo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import omar.lo.entities.MyRole;

public interface MyRoleRepository extends JpaRepository<MyRole, Long> {
	
	public MyRole findByRoleName(String roleName);

}
