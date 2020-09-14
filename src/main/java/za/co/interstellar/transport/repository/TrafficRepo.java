/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.interstellar.transport.entity.Traffic;

/**
 *
 * @author VukosiNyeleti
 */
@Repository
public interface TrafficRepo extends JpaRepository<Traffic, Long>{

}
