package omar.lo.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import omar.lo.entities.Tache;

//@RepositoryRestResource
public interface TacheRepository extends JpaRepository<Tache, Long> {
	
	//@Query("select t from Tache t where t.nom like :x")
	@Query("select t from Tache t where t.nom like :x Order By t.dateCreation DESC")
	public Page<Tache> chercher(@Param("x") String motCle, Pageable pageable);
}
