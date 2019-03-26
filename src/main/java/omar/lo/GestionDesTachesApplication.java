package omar.lo;

import java.util.Date;
import java.util.stream.Stream;

import omar.lo.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import omar.lo.entities.MyRole;
import omar.lo.entities.MyUser;
import omar.lo.entities.Tache;
import omar.lo.metier.TacheImpl;
import omar.lo.service.AccountServiceImpl;

@SpringBootApplication
public class GestionDesTachesApplication implements CommandLineRunner{

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	@Autowired
	private TacheImpl tacheImpl;
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	
	public static void main(String[] args) {
		SpringApplication.run(GestionDesTachesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Contact.class);

		/*Stream.of("T1", "T2", "T3", "T4", "T5", "T6", "T7", "T8", "T9", "T10", "T11"
				, "T12", "T13", "T14", "T15", "T16", "T17", "T18", "T19", "T20", "T21", "T22"
				, "T23", "T24", "T25", "T26", "T27", "T28", "T29", "T30", "T31").forEach(t ->{
			tacheImpl.saveTache(new Tache(t, null, new Date()));
		}); 
		
		tacheImpl.getTaches().forEach(t ->{
			System.out.println(t.getNom());
		});
		tacheImpl.getTaches().forEach(System.out::println);
		
		accountServiceImpl.saveUser(new MyUser("admin", "123", "admin@gmail.com"));
		accountServiceImpl.saveUser(new MyUser("user", "123", "user@gmail.com"));
		
		accountServiceImpl.saveRole(new MyRole("ADMIN"));
		accountServiceImpl.saveRole(new MyRole("USER"));
		
		accountServiceImpl.addRoleToUser("admin", "ADMIN");
		accountServiceImpl.addRoleToUser("admin", "USER");				
		accountServiceImpl.addRoleToUser("user", "USER");


		contactImpl.addContact(new Contact(null, "Lo", "Omar", "344355663", "lk@cbtn.ch", new Date()));
		contactImpl.addContact(new Contact(null, "Lo1", "Omar", "344355663", "lk@cbtn.ch", new Date()));
		*/
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}// GestionDesTachesApplication
