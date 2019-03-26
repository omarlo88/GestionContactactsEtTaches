package omar.lo.myConfig;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import omar.lo.entities.MyUser;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	private ObjectMapper objectMapper;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
		super();
		this.authenticationManager = authenticationManager;
		this.objectMapper = objectMapper;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		
		MyUser myUser = null;
		
		/*String username = request.getParameter("username");
		String password = request.getParameter("password"); pas possible dans ce 
														cas car les données sont envoyées en format json*/
		
		try {
			myUser = objectMapper.readValue(request.getInputStream(), MyUser.class);// ObjectMapper pour désérialiser les données de les mettre dans l'objet MyUser
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		System.out.println("*********************************");
		System.out.println("username: " + myUser.getUsername());
		System.out.println("password: " + myUser.getPassword());
		System.out.println("*********************************");
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(myUser.getUsername(), myUser.getPassword()));
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		User springUser = (User) authResult.getPrincipal();
		String jwt = Jwts.builder()
				.setSubject(springUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.claim("roles", springUser.getAuthorities())
				.compact();
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwt);
	}
	
}// JWTAuthenticationFilter
