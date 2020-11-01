package ph.eraine.poc.multipleauth.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ph.eraine.poc.multipleauth.model.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

    Slice<Game> findAll(Pageable pageable);

}