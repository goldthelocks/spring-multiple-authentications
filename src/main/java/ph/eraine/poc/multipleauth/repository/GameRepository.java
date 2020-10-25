package ph.eraine.poc.multipleauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.eraine.poc.multipleauth.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}