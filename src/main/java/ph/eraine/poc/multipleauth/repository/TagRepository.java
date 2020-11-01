package ph.eraine.poc.multipleauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.eraine.poc.multipleauth.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Integer countByNameIgnoreCase(String name);

    Tag findByNameIgnoreCase(String name);

}