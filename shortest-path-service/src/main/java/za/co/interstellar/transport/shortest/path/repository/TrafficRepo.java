package za.co.interstellar.transport.shortest.path.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.interstellar.transport.shortest.path.entity.Traffic;

@Repository
public interface TrafficRepo extends JpaRepository<Traffic, Long>{

}
