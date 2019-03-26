package omar.lo.metier;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import omar.lo.dao.TacheRepository;
import omar.lo.entities.Tache;

@Service
@Transactional
public class TacheImpl implements ITache {

	@Autowired
	private TacheRepository tacheRepository;

	@Override
	public List<Tache> getTaches() {
		return tacheRepository.findAll();
	}

	@Override
	public Tache getTache(Long id) {
		return tacheRepository.findOne(id);
	}

	@Override
	public Tache saveTache(Tache t) {
		return tacheRepository.save(t);
	}

	@Override
	public void deleteTache(Long id) {
		tacheRepository.delete(id);
	}

	@Override
	public Page<Tache> chercher(String motCle, int page, int size) {
		// TODO Auto-generated method stub
		return tacheRepository.chercher(motCle, 
				//new PageRequest(page, size, new Sort(Direction.ASC, "nom")));
				new PageRequest(page, size));
	}

}// TacheImpl
