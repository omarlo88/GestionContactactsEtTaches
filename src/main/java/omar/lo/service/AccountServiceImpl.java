package omar.lo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import omar.lo.dao.MyRoleRepository;
import omar.lo.dao.MyUserRepository;
import omar.lo.entities.MyRole;
import omar.lo.entities.MyUser;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private MyUserRepository myUserRepository;
	@Autowired
	private MyRoleRepository myRoleRepository;
	
	@Override
	public MyUser saveUser(MyUser user) {
		// TODO Auto-generated method stub
		String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		return myUserRepository.save(user);
	}

	@Override
	public MyRole saveRole(MyRole role) {
		// TODO Auto-generated method stub
		return myRoleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		
		MyRole role = myRoleRepository.findByRoleName(roleName);
		MyUser user = myUserRepository.findByUsername(username);
		if (user.getRoles().contains(role)) {
			throw new RuntimeException("Cet utilisateur a déjà ce rôle!!");
		}
		user.getRoles().add(role);
	}

	@Override
	public MyUser findByUsername(String username) {
		// TODO Auto-generated method stub
		return myUserRepository.findByUsername(username);
	}

	@Override
	public MyUser findByEmail(String email) {
		// TODO Auto-generated method stub
		return myUserRepository.findByEmail(email);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		myUserRepository.delete(id);
	}

	@Override
	public void deleteRole(Long id) {
		// TODO Auto-generated method stub
		myRoleRepository.delete(id);
	}

	@Override
	public List<MyUser> getUsers() {
		// TODO Auto-generated method stub
		return myUserRepository.findAll();
	}

	@Override
	public MyUser getUser(Long id) {
		// TODO Auto-generated method stub
		return myUserRepository.findOne(id);
	}

	@Override
	public void removeRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		MyRole role = myRoleRepository.findByRoleName(roleName);
		MyUser user = myUserRepository.findByUsername(username);
		if (user.getRoles().contains(role)) {
			user.getRoles().remove(role);
		} else {
			throw new RuntimeException("Cet utilisateur n'avait pas ce rôle!!");
		}
		
	}

	@Override
	public List<MyRole> getRoles() {
		// TODO Auto-generated method stub
		return myRoleRepository.findAll();
	}

}// AccountServiceImpl
