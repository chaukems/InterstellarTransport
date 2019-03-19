/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.repository;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import za.co.interstellar.transport.entity.Planet;

/**
 *
 * @author VukosiNyeleti
 */
@Repository("planetsRepo")
public class PlanetsRepoImpl extends AbstractDao<Long, Planet> implements PlanetsRepo {

    @Override
    public Planet findById(long id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));

        return (Planet) crit.uniqueResult();
    }

    @Override
    public void save(Planet planet) {
        persist(planet);
    }

    @Override
    public List<Planet> findAll() {
        Criteria crit = createEntityCriteria();
        return crit.list();
    }

    @Override
    public Planet findByNode(String node) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("planetNode", node));
        return (Planet) crit.uniqueResult();
    }

}
