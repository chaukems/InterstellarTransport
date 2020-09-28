package za.co.interstellar.transport.shortest.path.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.interstellar.transport.shortest.path.entity.Planet;
import za.co.interstellar.transport.shortest.path.entity.Route;

@Repository("routesRepo")
public interface RoutesRepo extends JpaRepository<Route, Long> {

    public List<Route> findByPlanetOrigin(Planet planetOrigin);

    public Route findByPlanetOriginAndPlanetDestination(Planet planetOrigin, Planet destinationPlanet);

    public List<Route> findByPlanetDestination(Planet planetDestination);

}
