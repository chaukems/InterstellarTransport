/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.interstellar.transport.entity.Route;

/**
 *
 * @author VukosiNyeleti
 */
@Repository("routesRepo")
public interface RoutesRepo extends JpaRepository<Route, Long>{

    public List<Route> findByPlanetOrigin(String planetOrigin);

}
