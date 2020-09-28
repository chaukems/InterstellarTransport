/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.shortest.path.util;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import za.co.interstellar.transport.shortest.path.entity.Planet;
import za.co.interstellar.transport.shortest.path.entity.Route;

/**
 *
 * @author schauke
 */
@Data
@AllArgsConstructor
@ToString
public class Graph {

    private final List<Planet> nodes;
    private final List<Route> routes;

}
