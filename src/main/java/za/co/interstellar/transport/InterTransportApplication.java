/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author VukosiNyeleti
 */
@SpringBootApplication(exclude=HibernateJpaAutoConfiguration.class)
@ComponentScan
public class InterTransportApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterTransportApplication.class, args);
    }

}
