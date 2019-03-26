package omar.lo.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import omar.lo.entities.MyRole;
import omar.lo.entities.MyUser;

@RestController
@RequestMapping("/AccountRestController")
public class AccountRestController {
	
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@GetMapping("/users")
	public List<MyUser> getUsers(){
		return accountServiceImpl.getUsers();
	}
	
	@GetMapping("/roles")
	public List<MyRole> getRoles(){
		return accountServiceImpl.getRoles();
	}
	
	@GetMapping("/users/{id}")
	public MyUser getUser(@PathVariable Long id){
		return accountServiceImpl.getUser(id);
	}
	
	@PostMapping("/register")
	public MyUser register(@RequestBody MyUsreForm userForm) {
		if (!userForm.getPassword().equals(userForm.getRepassword())) {
			throw new RuntimeException("Vous devez confirmer votre mot de passe!!");
		}
		MyUser userByUsername = accountServiceImpl.findByUsername(userForm.getUsername());
		MyUser userByEmail = accountServiceImpl.findByEmail(userForm.getEmail());
		if (userByUsername != null || userByEmail != null) {
			throw new RuntimeException("Cet utilisateur existe déjà!!");
		}
		
		MyUser myUser = new MyUser();
		myUser.setNom(userForm.getNom());
		myUser.setPrenom(userForm.getPrenom());
		myUser.setUsername(userForm.getUsername());
		myUser.setPassword(userForm.getPassword());
		myUser.setEmail(userForm.getEmail());
		accountServiceImpl.saveUser(myUser);
		accountServiceImpl.addRoleToUser(userForm.getUsername(), "USER");
		return myUser;
	}
	
	@PutMapping("/users/{id}")
	public MyUser updateUser(@PathVariable("id") Long id, @RequestBody MyUser user) {
		user.setId(id);
		return accountServiceImpl.saveUser(user);
	}
	
	@PutMapping("/addRoleToUser")
	public ResponseEntity<String> addRoleToUser(
			//@RequestParam(name="username") String username,
			@RequestParam String username,
			@RequestParam(name="roleName", defaultValue= "USER") String roleName) {
		accountServiceImpl.addRoleToUser(username, roleName);
		return new ResponseEntity<String>("Ce role a été attribué à cet utilisateur!!", HttpStatus.CREATED);
	}
	
	@DeleteMapping("/removeRoleToUser")
	public ResponseEntity<String> removeRoleToUser (
			@RequestParam(name="username") String username,
			@RequestParam(name="roleName") String roleName ){
		accountServiceImpl.removeRoleToUser(username, roleName);
		return new ResponseEntity<String>("Ce rôle a été suppimé pour cet Utilisateur!!", HttpStatus.OK);		
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteUser (@PathVariable("id") Long id){
		accountServiceImpl.deleteUser(id);
		return new ResponseEntity<String>("Cet utilisateur a été suppimé!!", HttpStatus.OK);		
	}
	
	@PostMapping("/roles")
	public MyRole saveRole(@RequestBody @Valid MyRole role) {
		return accountServiceImpl.saveRole(role);
	}
	
	@PutMapping("/roles/{id}")
	public MyRole updateRole(@PathVariable Long id, @RequestBody MyRole role) {
		role.setId(id);
		return accountServiceImpl.saveRole(role);
	}
	
	@DeleteMapping("/roles/{id}")
	public ResponseEntity<String> deleteRole (@PathVariable Long id){
		accountServiceImpl.deleteRole(id);
		return new ResponseEntity<String>("Ce rôle a été suppimé!!", HttpStatus.OK);		
	}

}// AccountRestController
