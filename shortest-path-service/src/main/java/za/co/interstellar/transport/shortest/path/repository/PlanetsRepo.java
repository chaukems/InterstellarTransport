package za.co.interstellar.transport.shortest.path.repository;

import za.co.interstellar.transport.shortest.path.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetsRepo extends JpaRepository<Planet, Long> {
    Planet findByPlanetNode(String node);
}
