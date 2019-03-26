package omar.lo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import omar.lo.entities.MyUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MyUser user = accountServiceImpl.findByUsername(username);
		if (user == null) { throw new UsernameNotFoundException(username);}
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}// UserDetailsServiceImpl
