/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.rest;

import com.google.gson.Gson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static za.co.interstellar.transport.DataLoader.computePaths;
import static za.co.interstellar.transport.DataLoader.getShortestPathTo;
import za.co.interstellar.transport.entity.Planet;
import za.co.interstellar.transport.entity.Route;
import za.co.interstellar.transport.repository.PlanetsRepo;
import za.co.interstellar.transport.repository.RoutesRepo;
import za.co.interstellar.transport.util.Edge;
import za.co.interstellar.transport.util.RequestDto;
import za.co.interstellar.transport.util.ResponseDto;
import za.co.interstellar.transport.util.Vertex;

/**
 *
 * @author VukosiNyeleti
 */
@RestController
@RequestMapping("/interstellar/transport")
@EnableWebMvc
public class TransportDataRestController {

    @Autowired
    private RoutesRepo routesRepo;

    @Autowired
    private PlanetsRepo planetRepo;

    @Autowired
    private Gson gson;

    @PostMapping(path = "/getDistance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getDistance(@RequestBody RequestDto requestDto) {

        Planet sourcePlanet = planetRepo.findByNode(requestDto.getSource().toUpperCase());
        Planet destinationPlanet = planetRepo.findByNode(requestDto.getDestination().toUpperCase());

        if (sourcePlanet == null && destinationPlanet == null) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setErrorCode("400");
            responseDto.setErrorMessage("Bad request.");
            return responseDto;
        }

        System.out.println("Source Planet " + gson.toJson(sourcePlanet));
        Vertex source = new Vertex(sourcePlanet.getPlanetNode());

        List<Route> sourcePlanetRoutes = routesRepo.findAll();

        Edge[] adjacencies = new Edge[sourcePlanetRoutes.size()];
        int i = 0;

        for (Route route : sourcePlanetRoutes) {

            adjacencies[i] = new Edge(source, route.getDistance());
            i++;
        }
        source.adjacencies = adjacencies;

        System.out.println("Destination Planet " + gson.toJson(destinationPlanet));
        Vertex destination = new Vertex(destinationPlanet.getPlanetNode());

        destination.adjacencies = adjacencies;

        computePaths(source);
        List<Vertex> path = getShortestPathTo(destination);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setDistance(destination.minDistance + "");
        responseDto.setPath(path.toString());

        return responseDto;

    }

}
