/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.repository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import za.co.interstellar.transport.entity.Route;

/**
 *
 * @author VukosiNyeleti
 */
@Transactional
public interface RoutesRepo {

    Route findById(long id);

    List<Route> findAll();

    void save(Route route);

    public List<Route> findByPlanetNode(String node);

}
