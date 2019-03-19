/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.repository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import za.co.interstellar.transport.entity.Traffic;

/**
 *
 * @author VukosiNyeleti
 */
@Transactional
public interface TrafficRepo {

    Traffic findById(long id);

    List<Traffic> findAll();

    void save(Traffic traffic);

}
