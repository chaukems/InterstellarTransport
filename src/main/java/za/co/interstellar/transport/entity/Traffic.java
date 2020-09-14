/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Traffic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String planetOrigin;
    private String planetDestination;
    private BigDecimal trafficDelay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanetOrigin() {
        return planetOrigin;
    }

    public void setPlanetOrigin(String planetOrigin) {
        this.planetOrigin = planetOrigin;
    }

    public String getPlanetDestination() {
        return planetDestination;
    }

    public void setPlanetDestination(String planetDestination) {
        this.planetDestination = planetDestination;
    }

    public BigDecimal getTrafficDelay() {
        return trafficDelay;
    }

    public void setTrafficDelay(BigDecimal trafficDelay) {
        this.trafficDelay = trafficDelay;
    }

}
