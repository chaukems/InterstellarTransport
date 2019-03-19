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
import za.co.interstellar.transport.entity.Traffic;

/**
 *
 * @author VukosiNyeleti
 */
@Repository("trafficRepo")
public class TrafficRepoImpl extends AbstractDao<Long, Traffic> implements TrafficRepo {

    @Override
    public Traffic findById(long id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        return (Traffic) crit.uniqueResult();
    }

    @Override
    public void save(Traffic traffic) {
        persist(traffic);
    }

    @Override
    public List<Traffic> findAll() {
        Criteria crit = createEntityCriteria();
        return crit.list();
    }

}
