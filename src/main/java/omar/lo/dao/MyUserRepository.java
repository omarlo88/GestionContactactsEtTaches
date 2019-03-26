package omar.lo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import omar.lo.entities.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long>{
	
	public MyUser findByUsername(String username);
	public MyUser findByEmail(String email);
}
