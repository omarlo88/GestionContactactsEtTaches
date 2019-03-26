package omar.lo.service;

import java.util.List;

import omar.lo.entities.MyRole;
import omar.lo.entities.MyUser;

public interface IAccountService {

	public MyUser saveUser(MyUser user);
	public MyRole saveRole(MyRole role);
	public void addRoleToUser(String username, String roleName);
	public void removeRoleToUser(String username, String roleName);
	public MyUser findByUsername(String username);
	public MyUser findByEmail(String email);
	public List<MyUser> getUsers();
	public List<MyRole> getRoles();
	public MyUser getUser(Long id);
	public void deleteUser(Long id);
	public void deleteRole(Long id);
}
