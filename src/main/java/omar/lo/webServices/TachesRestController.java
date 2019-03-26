package omar.lo.webServices;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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

import omar.lo.entities.Tache;
import omar.lo.metier.TacheImpl;

@RestController
//@RequestMapping(value="/TachesRestController")
@RequestMapping("/TachesRestController")
public class TachesRestController {

	@Autowired
	private TacheImpl tacheImpl;
	
	@GetMapping("/taches")
	public List<Tache> getTaches(){
		return tacheImpl.getTaches();
	}
	
	@GetMapping("/chercherTaches")
	public Page<Tache> chercherTache(
			@RequestParam(name="motCle", defaultValue="") String motCle,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size){
		return tacheImpl.chercher("%" + motCle +  "%", page, size);
	}
	
	@GetMapping("/taches/{id}")
	public Tache getTache(@PathVariable Long id){
		return tacheImpl.getTache(id);
	}
	
	@PostMapping("/taches")
	public Tache saveTache(@RequestBody @ Valid Tache t){
		t.setDateCreation(new Date());
		return tacheImpl.saveTache(t);
	}
	
	@PutMapping("/taches/{id}")
	public Tache updateTache (@PathVariable Long id, @RequestBody @Valid Tache t){
		t.setId(id);
		return tacheImpl.saveTache(t);
	}
	

	/*@DeleteMapping("/taches/{id}")
	public ResponseEntity<String> deleteTache (@PathVariable Long id){
		tacheImpl.deleteTache(id);
		//ResponseEntity.status(HttpStatus.OK).build();
		return new ResponseEntity<String>("Cette tâche a été suppimée!!", HttpStatus.OK);		
	}*/
	
	@DeleteMapping("/taches/{id}")
	public void deleteTache (@PathVariable Long id){
		tacheImpl.deleteTache(id);
		//ResponseEntity.status(HttpStatus.OK).build();
		//return new ResponseEntity<String>("Cette tâche a été suppimée!!", HttpStatus.OK);		
	}
	
}// TachesRestController
