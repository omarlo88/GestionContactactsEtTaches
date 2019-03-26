package omar.lo.metier;

import java.util.List;

import org.springframework.data.domain.Page;

import omar.lo.entities.Tache;

public interface ITache {
	
	public List<Tache> getTaches();
	public Page<Tache> chercher(String motCle, int page,  int size);
	public Tache getTache(Long id);
	public Tache saveTache(Tache t);
	public void deleteTache(Long id);
}
