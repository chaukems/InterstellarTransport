/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.repository;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import za.co.interstellar.transport.entity.Route;

/**
 *
 * @author VukosiNyeleti
 */
@Repository("routesRepo")
public class RoutesRepoImpl extends AbstractDao<Long, Route> implements RoutesRepo {

    @Override
    public Route findById(long id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        return (Route) crit.uniqueResult();
    }

    @Override
    public void save(Route route) {
        persist(route);
    }

    @Override
    public List<Route> findAll() {
        Criteria crit = createEntityCriteria();
        return crit.list();
    }

    @Override
    public List<Route> findByPlanetNode(String node) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("planetOrigin", node));
        return crit.list();
    }

}
